/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.CommonConstants;
import com.limes.backend.constants.SQLScripts;
import com.limes.backend.enums.TestTypeEnum;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.persistence.entity.Assignment;
import com.limes.backend.persistence.entity.Solution;
import com.limes.backend.rest.model.TestOverviewResponseModel;
import com.limes.backend.rest.model.assignment.AssignmentResponseModel;
import com.limes.backend.rest.model.assignment.SolutionModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@RestController
public class TestController {

    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @GetMapping("/test/overview")
    public static List<TestOverviewResponseModel> getOverview(@RequestParam(name = "email", required = true) String email) {
        List<String> tests = (List<String>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_COMPLETED_TEST_BY_STUDENT, email), String.class);

        List<TestOverviewResponseModel> tor = new ArrayList<>();

        tor.add(new TestOverviewResponseModel(TestTypeEnum.FIRST.label, tests.contains(CommonConstants.FIRST_TEST_EVENT)));
        tor.add(new TestOverviewResponseModel(TestTypeEnum.SECOND.label, tests.contains(CommonConstants.SECOND_TEST_EVENT)));
        tor.add(new TestOverviewResponseModel(TestTypeEnum.LAST.label, tests.contains(CommonConstants.LAST_TEST_EVENT)));
        return tor;
    }

    @GetMapping("/test")
    public static List<Integer> getTestAssignments(@RequestParam(name = "testType", required = true) String testType) {
        if (testType.equals(TestTypeEnum.FIRST.label)) {
            return (List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_FIRST_TEST, Integer.class);
        } else if (testType.equals(TestTypeEnum.SECOND.label)) {
            return (List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_SECOND_TEST, Integer.class);
        } else if (testType.equals(TestTypeEnum.LAST.label)) {
            return (List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_LAST_TEST, Integer.class);
        }
        return null;
    }

    @GetMapping("/test/assignment")
    public static AssignmentResponseModel getTestAddignmentById(@RequestParam(name = "assignmentId", required = true) int assignmentId) {
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
    }
}
