/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.constants;

/**
 *
 * @author Mate Forster
 */
public class SQLScripts {
    public final static String CHECK_STUDENT_ALLREADY_EXISTS = "select exists(select * from student s where s.email = '%s')";
    public final static String INSERT_NEW_STUDENT = "insert into student(email,forename,surename,pwd_hash) values('%s','%s','%s',decode('%s','hex'))";
}
