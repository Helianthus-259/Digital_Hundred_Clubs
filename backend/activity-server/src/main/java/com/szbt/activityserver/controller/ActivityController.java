package com.szbt.activityserver.controller;

import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.service.ActivitymemberService;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ActivityDTO;
import org.example.dto.ActivityEffectGroup;
import org.example.dto.ActivityMemberDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.service.ClubClientService;
import org.example.service.StudentClientService;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/activity")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivitymemberService activitymemberService;


    @GetMapping("/getActivityMemberBySid")
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        return activitymemberService.getActivityMemberBySid(id);
    }

    @GetMapping("/activityInfo")
    public Object activityInfo(Integer activityId){
        return activityService.activityInfo(activityId);
    }

    @GetMapping("/queryActivityInfoByClubIdList")
    public List<List<Activity>> queryActivityInfoByClubIdList(@RequestParam List<Integer> idList){
        return activityService.queryActivityInfoByClubIdList(idList);
    }

    @GetMapping("/activitiesInfo")
    public Object activitiesInfo(){
        List<ActivityDTO> activities = activityService.activitiesInfo();
        List<Integer> clubIdList = activities.stream().map(ActivityDTO::getClubId).collect(Collectors.toList());
        System.out.println(clubIdList);
        return activityService.getActivitiesInfo(activities,clubIdList);
    }

    @GetMapping("/latestActivities")
    public Object getLatestActivities(Integer pageNumber, Integer pageSize){
        return activityService.getLatestActivities(pageNumber, pageSize);
    }

    @PostMapping("/newActivity")
    public Object addActivity(@ModelAttribute Activity activity){
        System.out.println(activity);
        return  activityService.addActivity(activity);
    }

    @PostMapping("/personalPerformance")
    public Object personalPerformance(Integer activityId, @ModelAttribute List<ActivityEffectGroup> activityEffectGroup){
        System.out.println(activityId);
        System.out.println(activityEffectGroup);
        Activity activity = activityService.getById(activityId);
        System.out.println(activity);
        List<String> studentNumberList = activityEffectGroup.stream().map(ActivityEffectGroup::getStudentNumber).collect(Collectors.toList());
        System.out.println(studentNumberList);
        return activitymemberService.personalPerformance(activity, activityEffectGroup, studentNumberList);
    }

    @PostMapping("/activityPerformance")
    public Object activityPerformance(@ModelAttribute Activity activity){
        return activityService.activityPerformance(activity);
    }

    @GetMapping("/clubActivityList")
    public Object queryClubActivityList(Integer clubId,Integer pageNumber, Integer pageSize)
    {
        return activityService.queryClubActivityList(clubId,pageNumber,pageSize);
    }

    @PostMapping("/joinActivity")
    public Object joinActivity(Integer activityId, String studentNumber)
    {
        Activity activity = activityService.getById(activityId);
        return activitymemberService.joinActivity(studentNumber,activity);
    }
}
