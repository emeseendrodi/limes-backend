/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

import com.limes.backend.rest.model.ResultResponseModel;

/**
 *
 * @author Mate Forster
 */
public class SolveAssignmentResponseModel extends ResultResponseModel{

    private boolean hasNextAssignmentInLecture;
    
    public SolveAssignmentResponseModel(boolean success, String errorMessage, boolean hasNextAssignmentInLecture) {
        super(success, errorMessage);
        this.hasNextAssignmentInLecture = hasNextAssignmentInLecture;
    }

    public boolean isHasNextAssignmentInLecture() {
        return hasNextAssignmentInLecture;
    }

    public void setHasNextAssignmentInLecture(boolean hasNextAssignmentInLecture) {
        this.hasNextAssignmentInLecture = hasNextAssignmentInLecture;
    }  
}
