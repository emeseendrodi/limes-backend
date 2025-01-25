/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

/**
 *
 * @author Mate Forster
 */
public class LectureOverviewRequestModel {

    private int weeklyLectureId;
    private String weeklyLectureTitle;
    private boolean isCompleted;

    public LectureOverviewRequestModel(int weeklyLectureId, String weeklyLectureTitle, boolean isCompleted) {
        this.weeklyLectureId = weeklyLectureId;
        this.weeklyLectureTitle = weeklyLectureTitle;
        this.isCompleted = isCompleted;
    }

    public LectureOverviewRequestModel() {
    }

    public int getWeeklyLectureId() {
        return weeklyLectureId;
    }

    public void setWeeklyLectureId(int weeklyLectureId) {
        this.weeklyLectureId = weeklyLectureId;
    }

    public String getWeeklyLectureTitle() {
        return weeklyLectureTitle;
    }

    public void setWeeklyLectureTitle(String weeklyLectureTitle) {
        this.weeklyLectureTitle = weeklyLectureTitle;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "LectureModel{" + "weeklyLectureId=" + weeklyLectureId + ", weeklyLectureTitle=" + weeklyLectureTitle + ", isCompleted=" + isCompleted + '}';
    }

}
