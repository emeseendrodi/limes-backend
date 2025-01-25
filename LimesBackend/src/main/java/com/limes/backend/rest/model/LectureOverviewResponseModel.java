/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

import java.util.List;

/**
 *
 * @author Mate Forster
 */
public class LectureOverviewResponseModel {
    private String weekTitle;
    private List<LectureOverviewRequestModel> weekylLecture;

    public LectureOverviewResponseModel(String weekTitle, List<LectureOverviewRequestModel> weekylLecture) {
        this.weekTitle = weekTitle;
        this.weekylLecture = weekylLecture;
    }

    public LectureOverviewResponseModel() {
    }
    

    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }

    public List<LectureOverviewRequestModel> getWeekylLecture() {
        return weekylLecture;
    }

    public void setWeekylLecture(List<LectureOverviewRequestModel> weekylLecture) {
        this.weekylLecture = weekylLecture;
    }
    
    
}
