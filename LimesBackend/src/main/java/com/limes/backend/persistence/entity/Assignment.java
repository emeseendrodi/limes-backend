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
public class Assignment {
    @Id
    public int id;
    private String title;
    private String picture;
    private int solution_id;

    public Assignment() {
    }

    public Assignment(int id, String title, String picture, int solution_id) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.solution_id = solution_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSolution_id() {
        return solution_id;
    }

    public void setSolution_id(int solution_id) {
        this.solution_id = solution_id;
    }

}
