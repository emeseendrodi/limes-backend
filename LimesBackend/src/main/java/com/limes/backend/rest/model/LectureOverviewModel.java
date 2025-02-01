/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mate Forster
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureOverviewModel {

    private int weeklyLectureId;
    private String weeklyLectureTitle;
    private boolean isCompleted;
}
