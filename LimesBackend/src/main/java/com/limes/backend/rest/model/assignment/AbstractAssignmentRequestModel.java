/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mate Forster
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAssignmentRequestModel {
//    @NotBlank
//    private String email;
    @Min(0)
    private int weeklyLectureId;   
}
