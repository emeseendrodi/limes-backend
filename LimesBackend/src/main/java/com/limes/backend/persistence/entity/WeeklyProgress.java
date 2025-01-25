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
public class WeeklyProgress {
    @Id
    private int id;
    private int week_id;
    private boolean exists;

    public WeeklyProgress() {
    }

    public WeeklyProgress(int id, int week_id, boolean exists) {
        this.id = id;
        this.week_id = week_id;
        this.exists = exists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek_id() {
        return week_id;
    }

    public void setWeek_id(int week_id) {
        this.week_id = week_id;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    
}
