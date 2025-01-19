/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

import com.limes.backend.persistence.entity.Student;

/**
 *
 * @author Mate Forster
 */
public class RegisterRequestModel extends Student{

    public RegisterRequestModel(String email, String forename, String surename, String password) {
        super(email, forename, surename, password);
    }
    
}
