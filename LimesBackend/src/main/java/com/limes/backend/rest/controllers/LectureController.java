/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.MessageConstants;
import com.limes.backend.constants.SQLScripts;
import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.persistence.entity.Assignment;
import com.limes.backend.persistence.entity.Solution;
import com.limes.backend.persistence.entity.WeeklyLectureOverview;
 import com.limes.backend.rest.model.LectureOverviewModel;
import com.limes.backend.rest.model.LectureOverviewResponseModel;
import com.limes.backend.rest.model.assignment.SolveAssignmentRequestModel;
import com.limes.backend.rest.model.assignment.AssignmentRequestModel;
import com.limes.backend.rest.model.assignment.AssignmentResponseModel;
import com.limes.backend.rest.model.assignment.PreviousAssignmentRequestModel;
import com.limes.backend.rest.model.assignment.SolutionModel;
import com.limes.backend.rest.model.assignment.SolveAssignmentResponseModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@Slf4j
@RestController
public class LectureController {


    @GetMapping("/lecture/overview")
    public List<LectureOverviewResponseModel> getOverview(@RequestParam(name = "email", required = true) @NotBlank String email) {
        List<WeeklyLectureOverview> weeklyOverview = (List<WeeklyLectureOverview>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_WEEKLY_LECTURE_OVERVIEW, email), WeeklyLectureOverview.class);
        List<LectureOverviewResponseModel> lomList = new ArrayList<>();

        Map<Integer, String> weekIds = new HashMap<>();
        for (WeeklyLectureOverview oneOw : weeklyOverview) {
            if (!weekIds.containsKey(oneOw.getWeek_id())) {
                weekIds.put(oneOw.getWeek_id(), oneOw.getWeek_title());
            }
        }
        weekIds.forEach((k, v) -> {
            LectureOverviewResponseModel oneModel = new LectureOverviewResponseModel();
            oneModel.setWeekTitle(v);
            oneModel.setWeekylLecture(new ArrayList<>());
            for (WeeklyLectureOverview wlm : weeklyOverview) {
                if (wlm.getWeek_id() == k) {
                    LectureOverviewModel lm = new LectureOverviewModel();
                    lm.setWeeklyLectureId(wlm.getWeekly_lecture_id());
                    lm.setWeeklyLectureTitle(wlm.getWeekly_lecture_title());
                    lm.setCompleted(wlm.is_completed());
                    oneModel.getWeekylLecture().add(lm);
                }
            }
            lomList.add(oneModel);
        });

        return lomList;
    }

    @GetMapping("/lecture/nextAssignment")
    public AssignmentResponseModel getNextAssignment(@Valid @RequestBody AssignmentRequestModel req) {
        Assignment ass = null;
        if (!req.isWeelkyLectureAllreadyCompleted()) {
            ass = (Assignment) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_NEXT_ASSIGNMENT_NORMAL, req.getWeeklyLectureId(), req.getEmail()), Assignment.class);
            if (ass == null || ass.getTitle().isBlank()) {
                ass = getFirstAssignmentForWeeklyLecture(req.getWeeklyLectureId());
            }
        } else if (req.getPreviousAssignemntId() > 0) {
            ass = (Assignment) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_NEXT_ASSIGNMENT_IN_WEEKLY_LECTURE_BY_PREV_ID, req.getWeeklyLectureId(), req.getPreviousAssignemntId()), Assignment.class);
        } else {
            ass = getFirstAssignmentForWeeklyLecture(req.getWeeklyLectureId());
        }

        List<Solution> solutions = (List<Solution>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_SOLUTIONS_BY_ASSIGNMENT_ID, ass.getSolution_id()), Solution.class);

        AssignmentResponseModel arm = new AssignmentResponseModel();
        arm.setAssignmentId(ass.getId());
        arm.setAssignmentTitle(ass.getTitle());
        arm.setPicture(ass.getPicture());
        List<SolutionModel> smList = new ArrayList<>();

        solutions.stream().forEach(s -> {
            smList.add(new SolutionModel(s.getPicture(), s.getTitle()));
        });
        arm.setSolution(smList);
        return arm;
    }

    private Assignment getFirstAssignmentForWeeklyLecture(int weeklyLectureId) {
        return (Assignment) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_FIRST_ASSIGNMENT_IN_WEEKLY_LECTURE, weeklyLectureId), Assignment.class);
    }

    @PostMapping("/lecture/solveAssignment")
    public SolveAssignmentResponseModel solveAssignment(@Valid @RequestBody SolveAssignmentRequestModel req) {
        try {
            int inserts = NativeSqlServices.insertNative(String.format(SQLScripts.INSERT_INTO_PLOG_ASSIGNMENT_SOLVED, req.getEmail(), req.getAssignmentId()));
            if (inserts > 0) {
                boolean hasMoreAssignments = (boolean) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_EXISTS_MORE_ASSIGNMENT_IN_WEEKLY_LECTURE_BY_ASSIGNMENT_ID, req.getAssignmentId(), req.getAssignmentId()), boolean.class);

                if (!hasMoreAssignments) {
                    inserts = NativeSqlServices.insertNative(String.format(SQLScripts.INSERT_WEEKLY_LECTURE_COMPLETED_BY_ASSIGNMENT_ID, req.getEmail(), req.getAssignmentId()));
                }

                if (inserts > 0) {
                    return new SolveAssignmentResponseModel(true, "", hasMoreAssignments);
                } else {
                    throw new LimesPersistenceException(MessageConstants.LOG_LOG_INSERT_ERROR);
                }

            } else {
                throw new LimesPersistenceException(MessageConstants.LOG_LOG_INSERT_ERROR);
            }
        } catch (LimesPersistenceException ex) {
            log.error(ex.getLocalizedMessage());
            return new SolveAssignmentResponseModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_SOLVE, false);
        }
    }

    @GetMapping("/lecture/previousAssignment")
    public AssignmentResponseModel previousAssignment(@Valid @RequestBody PreviousAssignmentRequestModel req) {
        try {
            int assignmentId = (int) NativeSqlServices.deleteNativeWithCustomResult(String.format(SQLScripts.DELETE_LAST_ASSIGNMENT_COMPLETE_FROM_PL_LOG_RETURN_ASSIGNMENT_ID, req.getEmail()), Integer.class);

            Assignment ass = (Assignment) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_ASSIGNMENT_BY_ID, assignmentId), Assignment.class);

            List<Solution> solutions = (List<Solution>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_SOLUTIONS_BY_ASSIGNMENT_ID, ass.getSolution_id()), Solution.class);

            AssignmentResponseModel arm = new AssignmentResponseModel();
            arm.setAssignmentId(ass.getId());
            arm.setAssignmentTitle(ass.getTitle());
            arm.setPicture(ass.getPicture());
            List<SolutionModel> smList = new ArrayList<>();

            solutions.stream().forEach(s -> {
                smList.add(new SolutionModel(s.getPicture(), s.getTitle()));
            });
            arm.setSolution(smList);
            return arm;
        } catch (LimesPersistenceException ex) {
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }
}
