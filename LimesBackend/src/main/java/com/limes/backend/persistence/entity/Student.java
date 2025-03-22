/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    private int id;
    @NotBlank
    private String email;
    @NotBlank
    private String forename;
    @NotBlank
    private String surename;
    private String password;
    
    public Student(String email, String forename, String surename, String password){
        this.email = email;
        this.forename = forename;
        this.surename = surename;
        this.password = password;
    }
}
