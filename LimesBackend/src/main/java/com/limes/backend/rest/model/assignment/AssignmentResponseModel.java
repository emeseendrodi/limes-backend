/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.model.assignment;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mate Forster
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponseModel extends AbstractPictureModel{
    private int assignmentId;
//    private int serialNumber;
    private String assignmentTitle;
    private List<SolutionModel> solution;  
}
