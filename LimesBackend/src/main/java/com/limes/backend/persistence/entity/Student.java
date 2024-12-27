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
public class Student {

    @Id
    private int id;
    private String email;
    private String forename;
    private String surename;
    private String pwd_hash;

    public Student(int id, String email, String forename, String surename, String pwd_hash) {
        this.email = email;
        this.forename = forename;
        this.surename = surename;
        this.pwd_hash = pwd_hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPwd_hash() {
        return pwd_hash;
    }

    public void setPwd_hash(String pwd_hash) {
        this.pwd_hash = pwd_hash;
    }
}
