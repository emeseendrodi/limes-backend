/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.rest.model.StudentModel;
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

    @PostMapping("/student")
    public String registerStudent(@RequestBody StudentModel newStudent) {
        try {
            int studentsInserted = NativeSqlServices.insertNative(String.format("insert into student(email,forename,surename,pwd_hash) values('%s','%s','%s','%s'))", newStudent.getEmail(), newStudent.getForename(), newStudent.getSurename(), newStudent.getPwd_hash()));
        } catch (LimesPersistenceException ex) {
            logger.error(ex);
        } finally {
            return "";
        }
    }
}
