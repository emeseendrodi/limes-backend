/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

/**
 *
 * @author Mate Forster
 */
public abstract class AbstractPictureModel {
    private String picture;
    private String title;

    public AbstractPictureModel() {
    }

    public AbstractPictureModel(String picture, String title) {
        this.picture = picture;
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
