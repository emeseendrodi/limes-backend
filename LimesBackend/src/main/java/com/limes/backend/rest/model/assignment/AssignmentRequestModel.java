/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

/**
 *
 * @author Mate Forster
 */
public class AssignmentRequestModel extends AbstractAssignmentRequestModel {
    private boolean isWeelkyLectureAllreadyCompleted;
    private int previousAssignemntId;
    
    public AssignmentRequestModel(boolean isWeelkyLectureAllreadyCompleted, String email, int weeklyLectureId, int previousAssignemntId) {
        super(email, weeklyLectureId);
        this.isWeelkyLectureAllreadyCompleted = isWeelkyLectureAllreadyCompleted;
        this.previousAssignemntId = previousAssignemntId;
    }

    public boolean isIsWeelkyLectureAllreadyCompleted() {
        return isWeelkyLectureAllreadyCompleted;
    }

    public void setIsWeelkyLectureAllreadyCompleted(boolean isWeelkyLectureAllreadyCompleted) {
        this.isWeelkyLectureAllreadyCompleted = isWeelkyLectureAllreadyCompleted;
    }

    public int getPreviousAssignemntId() {
        return previousAssignemntId;
    }

    public void setPreviousAssignemntId(int previousAssignemntId) {
        this.previousAssignemntId = previousAssignemntId;
    }
    
}
