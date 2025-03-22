/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.CommonConstants;
import com.limes.backend.constants.MessageConstants;
import com.limes.backend.constants.SQLScripts;
import com.limes.backend.enums.TestTypeEnum;
import com.limes.backend.exception.jwt.LimesInvalidJwtTokenException;
import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.persistence.entity.Assignment;
import com.limes.backend.persistence.entity.Solution;
import com.limes.backend.rest.model.ResultResponseModel;
import com.limes.backend.rest.model.TestOverviewResponseModel;
import com.limes.backend.rest.model.TestSolveRequestModel;
import com.limes.backend.rest.model.assignment.AssignmentResponseModel;
import com.limes.backend.rest.model.assignment.SolutionModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestController extends AbstractController {

    @GetMapping("/test/overview")
    public ResponseEntity getOverview(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String auth) {
        String email;
        try {
            email = extractEmailFromToken(auth);
        } catch (LimesInvalidJwtTokenException ex) {
            return new ResponseEntity(new ResultResponseModel(false, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
        List<String> tests = (List<String>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_COMPLETED_TEST_BY_STUDENT, email), String.class);

        List<TestOverviewResponseModel> tor = new ArrayList<>();

        tor.add(new TestOverviewResponseModel(TestTypeEnum.FIRST.label, tests.contains(CommonConstants.FIRST_TEST_EVENT)));
        tor.add(new TestOverviewResponseModel(TestTypeEnum.SECOND.label, tests.contains(CommonConstants.SECOND_TEST_EVENT)));
        tor.add(new TestOverviewResponseModel(TestTypeEnum.LAST.label, tests.contains(CommonConstants.LAST_TEST_EVENT)));
        return new ResponseEntity(tor, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity getTestAssignments(@RequestParam(name = "testType", required = true) @NotBlank String testType) {
        if (testType.equals(TestTypeEnum.FIRST.label)) {
            return new ResponseEntity((List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_FIRST_TEST, Integer.class), HttpStatus.OK);
        } else if (testType.equals(TestTypeEnum.SECOND.label)) {
            return new ResponseEntity((List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_SECOND_TEST, Integer.class), HttpStatus.OK);
        } else if (testType.equals(TestTypeEnum.LAST.label)) {
            return new ResponseEntity((List<Integer>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_TEST_ASSIGNMENTS_FOR_LAST_TEST, Integer.class), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_FAULTY_TEST_TYPE), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test/assignment")
    public AssignmentResponseModel getTestAssignmentById(@RequestParam(name = "assignmentId", required = true) @Min(0) int assignmentId) {
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

    @PostMapping("/test/solve")
    public ResponseEntity solveTest(@RequestHeader(HttpHeaders.AUTHORIZATION) @NotBlank String auth, @Valid @RequestBody TestSolveRequestModel req) {
        String email;
        try {
            email = extractEmailFromToken(auth);
        } catch (LimesInvalidJwtTokenException ex) {
            return new ResponseEntity(new ResultResponseModel(false, ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }

        String te = null;

        if (req.getTestType().equals(TestTypeEnum.FIRST.label)) {
            te = CommonConstants.FIRST_TEST_EVENT;
        } else if (req.getTestType().equals(TestTypeEnum.SECOND.label)) {
            te = CommonConstants.SECOND_TEST_EVENT;
        } else if (req.getTestType().equals(TestTypeEnum.LAST.label)) {
            te = CommonConstants.LAST_TEST_EVENT;
        }

        if (te == null) {
            return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_FAULTY_TEST_TYPE), HttpStatus.BAD_REQUEST);
        }

        try {
            int inserts = NativeSqlServices.insertNative(String.format(SQLScripts.INSERT_TEST_SOLVED, email, te));

            if (inserts > 0) {
                return new ResponseEntity(new ResultResponseModel(true), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_SOLVE_TEST), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (LimesPersistenceException ex) {
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_SOLVE_TEST), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
