/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

/**
 *
 * @author Mate Forster
 */
public class TestOverviewResponseModel {
    private String title;
    private boolean isTestCompleted;

    public TestOverviewResponseModel() {
    }

    public TestOverviewResponseModel(String title, boolean isTestCompleted) {
        this.title = title;
        this.isTestCompleted = isTestCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsTestCompleted() {
        return isTestCompleted;
    }

    public void setIsTestCompleted(boolean isTestCompleted) {
        this.isTestCompleted = isTestCompleted;
    }
}
