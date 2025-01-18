/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.MessageConstants;
import com.limes.backend.constants.SQLScripts;
import com.limes.backend.exception.persistence.LimesPasswordHelperException;
import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.rest.model.ResultModel;
import com.limes.backend.rest.model.StudentModel;
import com.limes.backend.security.PasswordHelper;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@RestController
public class StudentController {

    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @PostMapping("/student/register")
    public ResultModel registerStudent(@RequestBody StudentModel newStudent){
        boolean allreadyExists = (boolean)NativeSqlServices.executeNativeQueryOneLiner(String.format(SQLScripts.CHECK_STUDENT_ALLREADY_EXISTS, newStudent.getEmail()));
        if (allreadyExists) {
            logger.warn(String.format(MessageConstants.LOG_USER_ALLREADY_EXISTS, newStudent.getEmail()));
            return new ResultModel(false, MessageConstants.MESSAGE_USER_ALLREADY_EXISTS);
        }else{
            try {
                NativeSqlServices.insertNative(String.format(SQLScripts.INSERT_NEW_STUDENT, newStudent.getEmail(), newStudent.getForename(), newStudent.getSurename(), new PasswordHelper().hashPassword(newStudent.getPassword())));
                return new ResultModel(true);
            } catch (LimesPersistenceException | LimesPasswordHelperException ex) {
                logger.error(ex);
                return new ResultModel(false, MessageConstants.MESSAGE_UNEXPECTED_ERROR_DURING_REGISTRATION);
            }
        }
    }
    
//    @PostMapping("/student/login")
//    public ResultModel loginStudent(){
//    }
}
