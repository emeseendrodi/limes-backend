/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.profile;

/**
 *
 * @author Mate Forster
 */
public class ProfileResponseModel {
    private String forename;
    private String surename;
    private String email;
    private ProgressionModel progression;

    public ProfileResponseModel() {
    }

    public ProfileResponseModel(String forename, String surename, String email, ProgressionModel progression) {
        this.forename = forename;
        this.surename = surename;
        this.email = email;
        this.progression = progression;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProgressionModel getProgression() {
        return progression;
    }

    public void setProgression(ProgressionModel progression) {
        this.progression = progression;
    }
}
