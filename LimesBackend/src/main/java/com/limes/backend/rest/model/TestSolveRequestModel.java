/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

/**
 *
 * @author Mate Forster
 */
public class TestSolveRequestModel {
    private String email;
    private String testType;

    public TestSolveRequestModel() {
    }

    public TestSolveRequestModel(String email, String testType) {
        this.email = email;
        this.testType = testType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
    
}
