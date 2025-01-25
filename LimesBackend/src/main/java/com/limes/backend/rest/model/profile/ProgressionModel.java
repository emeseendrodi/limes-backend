/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.profile;

import java.util.List;

/**
 *
 * @author Mate Forster
 */
public class ProgressionModel {
    private List<ProgressionObjectModel> completedWeeks;
    private List<ProgressionObjectModel> completedTests;

    public ProgressionModel() {
    }

    public ProgressionModel(List<ProgressionObjectModel> completedWeeks, List<ProgressionObjectModel> completedTests) {
        this.completedWeeks = completedWeeks;
        this.completedTests = completedTests;
    }

    public List<ProgressionObjectModel> getCompletedWeeks() {
        return completedWeeks;
    }

    public void setCompletedWeeks(List<ProgressionObjectModel> completedWeeks) {
        this.completedWeeks = completedWeeks;
    }

    public List<ProgressionObjectModel> getCompletedTests() {
        return completedTests;
    }

    public void setCompletedTests(List<ProgressionObjectModel> completedTests) {
        this.completedTests = completedTests;
    }
}
