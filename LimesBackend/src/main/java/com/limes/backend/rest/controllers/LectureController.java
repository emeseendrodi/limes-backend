/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.rest.controllers;

import com.limes.backend.constants.SQLScripts;
import com.limes.backend.persistence.NativeSqlServices;
import com.limes.backend.persistence.entity.WeeklyLectureOverview;
import com.limes.backend.rest.model.LectureModel;
import com.limes.backend.rest.model.LectureOverviewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mate Forster
 */
@RestController
public class LectureController {

    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @GetMapping("/lecture/overview")
    public List<LectureOverviewModel> getOverview(@RequestParam(name = "email", required = true) String email) {
        List<WeeklyLectureOverview> weeklyOverview = (List<WeeklyLectureOverview>) NativeSqlServices.executeNativeQueryWithClassEnforce(String.format(SQLScripts.GET_WEEKLY_LECTURE_OVERVIEW, email), WeeklyLectureOverview.class);
        List<LectureOverviewModel> lomList = new ArrayList<>();

        Map<Integer, String> weekIds = new HashMap<>();
        for (WeeklyLectureOverview oneOw : weeklyOverview) {
            if (!weekIds.containsKey(oneOw.getWeek_id())) {
                weekIds.put(oneOw.getWeek_id(), oneOw.getWeek_title());
            }
        }
        weekIds.forEach((k, v) -> {
            LectureOverviewModel oneModel = new LectureOverviewModel();
            oneModel.setWeekTitle(v);
            oneModel.setWeekylLecture(new ArrayList<>());
            for (WeeklyLectureOverview wlm : weeklyOverview) {
                if (wlm.getWeek_id() == k) {
                    LectureModel lm = new LectureModel();
                    lm.setWeeklyLectureId(wlm.getWeekly_lecture_id());
                    lm.setWeeklyLectureTitle(wlm.getWeekly_lecture_title());
                    lm.setIsCompleted(wlm.isIs_completed());
                    oneModel.getWeekylLecture().add(lm);
                }
            }
            lomList.add(oneModel);
        });
        
        return lomList;
    }
}
