/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.CommonConstants;
import com.limes.backend.constants.MessageConstants;
import com.limes.backend.constants.SQLScripts;
import com.limes.backend.enums.TestTypeEnum;
import com.limes.backend.exception.persistence.LimesPasswordHelperException;
import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.persistence.entity.Student;
import com.limes.backend.persistence.entity.Week;
import com.limes.backend.persistence.entity.WeeklyProgress;
import com.limes.backend.rest.model.LoginRequestModel;
import com.limes.backend.rest.model.ResultResponseModel;
import com.limes.backend.rest.model.RegisterRequestModel;
import com.limes.backend.rest.model.profile.ProfileResponseModel;
import com.limes.backend.rest.model.profile.ProgressionModel;
import com.limes.backend.rest.model.profile.ProgressionObjectModel;
import com.limes.backend.security.PasswordHelper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class StudentController {

    @PostMapping("/student/register")
    public ResponseEntity registerStudent(@Valid @RequestBody RegisterRequestModel newStudent) {
        boolean allreadyExists = (boolean) NativeSqlServices.executeNativeQueryOneLiner(String.format(SQLScripts.CHECK_STUDENT_ALLREADY_EXISTS, newStudent.getEmail()));
        if (allreadyExists) {
            log.warn(String.format(MessageConstants.LOG_USER_ALLREADY_EXISTS, newStudent.getEmail()));
            return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_USER_ALLREADY_EXISTS),HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            try {
                int records = NativeSqlServices.insertNative(String.format(SQLScripts.INSERT_NEW_STUDENT, newStudent.getEmail(), newStudent.getForename(), newStudent.getSurename(), new PasswordHelper().hashPassword(newStudent.getPassword())));
                if (records>0) {
                     return new ResponseEntity(new ResultResponseModel(true),HttpStatus.OK);
                }else{
                     return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_REGISTRATION),HttpStatus.UNPROCESSABLE_ENTITY);
                }
            } catch (LimesPersistenceException | LimesPasswordHelperException ex) {
                log.error(ex.getLocalizedMessage());
                return  new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_REGISTRATION),HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
    }

    @PostMapping("/student/login")
    public ResponseEntity loginStudent(@Valid @RequestBody LoginRequestModel loginData) {
        String pwdHash = (String) NativeSqlServices.executeNativeQueryOneLiner(String.format(String.format(SQLScripts.GET_USER_PWD_HASH, loginData.getEmail())));
        if (!pwdHash.isBlank()) {
            try {
                System.out.println(pwdHash+" | "+new PasswordHelper().hashPassword(loginData.getPassword()));
                if (pwdHash.equalsIgnoreCase(new PasswordHelper().hashPassword(loginData.getPassword()))) {
                    return new ResponseEntity(new ResultResponseModel(true),HttpStatus.OK);
                }else{
                    return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_INCORRECT_PASSWORD),HttpStatus.OK);
                }
            } catch (LimesPasswordHelperException ex) {
                log.error(ex.getLocalizedMessage());
                return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESAGE_UNEXPECTED_ERROR_DURING_LOGIN),HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            log.warn(MessageConstants.LOG_NO_USER);
            return new ResponseEntity(new ResultResponseModel(false, MessageConstants.MESSAGE_USER_IS_NOT_REGISTERED),HttpStatus.OK);
        }
    }
    
    @GetMapping("/student/profile")
    public ProfileResponseModel getProfileData(@RequestParam(name = "email", required = true) @NotBlank String email){
        Student student = (Student) NativeSqlServices.executeNativeQueryWithClassEnforceOneLiner(String.format(SQLScripts.GET_STUDENT, email), Student.class);
        List<Week> weeks = (List<Week>) NativeSqlServices.executeNativeQueryWithClassEnforce(SQLScripts.GET_WEEKS, Week.class); 
        List<WeeklyProgress> progress = (List<WeeklyProgress>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_WEEKLY_LECTURE_PROGRESSION_FOR_USER, email), WeeklyProgress.class);
    
        ProfileResponseModel prm = new ProfileResponseModel();
        prm.setEmail(student.getEmail());
        prm.setForename(student.getForename());
        prm.setSurename(student.getSurename());
        
        ProgressionModel pm = new ProgressionModel();
        
        List<ProgressionObjectModel> weekProgress = new ArrayList();
        
        weeks.stream().forEach(w -> {weekProgress.add(new ProgressionObjectModel(w.getName(),!progress.stream().anyMatch(f -> (f.getWeek_id() == w.getId() && !f.isExists()))));});
        pm.setCompletedWeeks(weekProgress);
        
        List<String> tests = (List<String>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_COMPLETED_TEST_BY_STUDENT, email), String.class);
        List<ProgressionObjectModel> testProgress = new ArrayList();
        
        testProgress.add(new ProgressionObjectModel(TestTypeEnum.FIRST.value, tests.contains(CommonConstants.FIRST_TEST_EVENT)));
        testProgress.add(new ProgressionObjectModel(TestTypeEnum.SECOND.value, tests.contains(CommonConstants.SECOND_TEST_EVENT)));
        testProgress.add(new ProgressionObjectModel(TestTypeEnum.LAST.value, tests.contains(CommonConstants.LAST_TEST_EVENT)));
        pm.setCompletedTests(testProgress);
        
        prm.setProgression(pm);
        
        return prm;
    }
}
