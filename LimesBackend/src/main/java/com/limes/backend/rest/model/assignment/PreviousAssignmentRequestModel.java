/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;


public class PreviousAssignmentRequestModel extends AbstractAssignmentRequestModel{

    public PreviousAssignmentRequestModel() {
    }

    public PreviousAssignmentRequestModel(String email, int weeklyLectureId) {
        super(weeklyLectureId);
    }
    
}
