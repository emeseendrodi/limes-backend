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
    public final static String GET_NEXT_ASSIGNMENT_NORMAL = "select a.id, a.description as title, ap.img as picture,a.solution_id from assignment a join assignment_pic ap on a.id = ap.assingment_id  where a.weekly_lecture_id = %d and a.is_test_assignment is false and a.id > (select cast(pl.progression_id as integer)  from progression_log pl where pl.progression_tpye_id = (select pt.id from progression_type pt where pt.name = 'ASSIGNMENT_SOLVED') and pl.student_id = (select s.id from student s where s.email = '%s') order by pl.id desc limit 1) order by a.id asc, ap.priority asc";
    public final static String GET_SOLUTIONS_BY_ASSIGNMENT_ID = "select s.id,s.description as title,sp.img as picture from solution s left join solution_pic sp on s.id = sp.solution_id where s.id = %d order by sp.priority asc";
    public final static String GET_FIRST_ASSIGNMENT_IN_WEEKLY_LECTURE = "select a.id, a.description as title, ap.img as picture,a.solution_id  from assignment a join assignment_pic ap on a.id = ap.assingment_id  where a.weekly_lecture_id = %d and a.is_test_assignment is false order by a.id asc, ap.priority asc limit 1";
    public final static String GET_NEXT_ASSIGNMENT_IN_WEEKLY_LECTURE_BY_PREV_ID = "select a.id, a.description as title, ap.img as picture,a.solution_id  from assignment a join assignment_pic ap on a.id = ap.assingment_id  where a.weekly_lecture_id = %d and a.is_test_assignment is false and a.id > %d order by a.id asc, ap.priority asc limit 1";
    public final static String INSERT_INTO_PLOG_ASSIGNMENT_SOLVED = "insert into progression_log (student_id,progression_tpye_id,progression_id) values((select s.id from student s where s.email = '%s'),(select pt.id from progression_type pt where pt.name = 'ASSIGNMENT_SOLVED'),%d)";
    public final static String GET_EXISTS_MORE_ASSIGNMENT_IN_WEEKLY_LECTURE_BY_ASSIGNMENT_ID = "select exists(select * from assignment a where a.weekly_lecture_id = (select a2.weekly_lecture_id from assignment a2 where a2.id = %d) and a.id > %d)";
}
