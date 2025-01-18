/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author Mate Forster
 */
@Entity
public class WeeklyLectureOverview {

    private int week_id;
    private String week_title;
    private String weekly_lecture_title;
    @Id
    private int weekly_lecture_id;
    private boolean is_completed;

    public WeeklyLectureOverview() {
    }

    public WeeklyLectureOverview(int week_id, String week_title, int weekly_lecture_id, String weekly_lecture_title, boolean is_completed) {
        this.week_id = week_id;
        this.week_title = week_title;
        this.weekly_lecture_title = weekly_lecture_title;
        this.weekly_lecture_id = weekly_lecture_id;
        this.is_completed = is_completed;
    }

    public int getWeek_id() {
        return week_id;
    }

    public void setWeek_id(int week_id) {
        this.week_id = week_id;
    }

    public String getWeek_title() {
        return week_title;
    }

    public void setWeek_title(String week_title) {
        this.week_title = week_title;
    }

    public int getWeekly_lecture_id() {
        return weekly_lecture_id;
    }

    public void setWeekly_lecture_id(int weekly_lecture_id) {
        this.weekly_lecture_id = weekly_lecture_id;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    public String getWeekly_lecture_title() {
        return weekly_lecture_title;
    }

    public void setWeekly_lecture_title(String weekly_lecture_title) {
        this.weekly_lecture_title = weekly_lecture_title;
    }

    @Override
    public String toString() {
        return "WeeklyLectureOverview{" + "week_id=" + week_id + ", week_title=" + week_title + ", weekly_lecture_title=" + weekly_lecture_title + ", weekly_lecture_id=" + weekly_lecture_id + ", is_completed=" + is_completed + "} ||";
    }

}
