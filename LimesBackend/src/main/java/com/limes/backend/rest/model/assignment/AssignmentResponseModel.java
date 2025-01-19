/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

import java.util.List;

/**
 *
 * @author Mate Forster
 */
public class AssignmentResponseModel extends AbstractPictureModel{
    private int assignmentId;
//    private int serialNumber;
    private String assignmentTitle;
    private List<SolutionModel> solution;

    public AssignmentResponseModel() {
    }
    
    public AssignmentResponseModel(int assignmentId, String assignmentTitle, List<SolutionModel> solution, String picture, String title) {
        super(picture, title);
        this.assignmentId = assignmentId;
//        this.serialNumber = serialNumber;
        this.assignmentTitle = assignmentTitle;
        this.solution = solution;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

//    public int getSerialNumber() {
//        return serialNumber;
//    }

//    public void setSerialNumber(int serialNumber) {
//        this.serialNumber = serialNumber;
//    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public List<SolutionModel> getSolution() {
        return solution;
    }

    public void setSolution(List<SolutionModel> solution) {
        this.solution = solution;
    }
    
}
