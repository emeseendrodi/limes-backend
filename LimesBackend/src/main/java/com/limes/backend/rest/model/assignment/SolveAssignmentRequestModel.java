/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

/**
 *
 * @author Mate Forster
 */
public class SolveAssignmentRequestModel {
    private String email;
    private int assignmentId;

    public SolveAssignmentRequestModel() {
    }

    public SolveAssignmentRequestModel(String email, int assignmentId) {
        this.email = email;
        this.assignmentId = assignmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
}
