/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.exception.persistence.LimesPersistenceException;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.rest.model.StudentModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@RestController
public class StudentController {

    @PostMapping("/student")
    public String registerStudent(@RequestBody StudentModel newStudent) {
        try {
            int studentsInserted = NativeSqlServices.insertNative("insert into student(email,forename,surename,pwd_hash) values('%s','%s','%s','%s'))");
        } catch (LimesPersistenceException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return "";
        }
    }
}
