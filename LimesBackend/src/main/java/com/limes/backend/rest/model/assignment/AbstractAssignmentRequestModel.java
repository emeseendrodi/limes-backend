/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

/**
 *
 * @author Mate Forster
 */
public abstract class AbstractAssignmentRequestModel {
    private String email;
    private int weeklyLectureId;

    public AbstractAssignmentRequestModel() {
    }

    public AbstractAssignmentRequestModel(String email, int weeklyLectureId) {
        this.email = email;
        this.weeklyLectureId = weeklyLectureId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWeeklyLectureId() {
        return weeklyLectureId;
    }

    public void setWeeklyLectureId(int weeklyLectureId) {
        this.weeklyLectureId = weeklyLectureId;
    }
    
}
