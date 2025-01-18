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
    public final static String GET_USER_PWD_HASH = "select encode(s.pwd_hash,'hex') from student s where s.email = '%s'";
    public final static String GET_WEEKLY_LECTURE_OVERVIEW = "select w.id as week_id, w.name as week_title,wl.id as weekly_lecture_id,wl.title as weekly_lecture_title, (select exists(select * from progression_log pl where pl.student_id = (select s.id from student s where s.email = '%s') and pl.progression_tpye_id = (select pt.id from progression_type pt where pt.name = 'WEEKLY_LECTURE_COMPLETED') and pl.progression_id = cast(wl.id as varchar))) as is_completed from weekly_lecture wl left join week w on wl.week_id = w.id";
}
