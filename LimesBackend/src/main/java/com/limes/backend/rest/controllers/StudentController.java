/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.rest.model.StudentModel;
import java.util.List;
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
    //@RequestBody StudentModel newStudent
    public String registerStudent(){
       List<StudentModel> list =  (List<StudentModel>) NativeSqlServices.executeNativeQueryWithClassEnforce("select * from public.student", StudentModel.class);
        return list.get(0).getEmail();
    }
}
