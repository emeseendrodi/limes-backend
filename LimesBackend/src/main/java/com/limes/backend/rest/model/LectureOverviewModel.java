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
public class LectureOverviewModel {
    private String weekTitle;
    private List<LectureModel> weekylLecture;

    public LectureOverviewModel(String weekTitle, List<LectureModel> weekylLecture) {
        this.weekTitle = weekTitle;
        this.weekylLecture = weekylLecture;
    }

    public LectureOverviewModel() {
    }
    

    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }

    public List<LectureModel> getWeekylLecture() {
        return weekylLecture;
    }

    public void setWeekylLecture(List<LectureModel> weekylLecture) {
        this.weekylLecture = weekylLecture;
    }
    
    
}
