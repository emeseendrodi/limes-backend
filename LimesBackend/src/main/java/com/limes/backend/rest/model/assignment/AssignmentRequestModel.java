/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

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
public class AssignmentRequestModel extends AbstractAssignmentRequestModel {
    private boolean isWeelkyLectureAllreadyCompleted;
    private int previousAssignemntId;
}
