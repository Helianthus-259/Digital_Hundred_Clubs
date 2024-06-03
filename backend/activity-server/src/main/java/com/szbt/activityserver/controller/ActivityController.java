package com.szbt.activityserver.controller;

import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.service.ActivitymemberService;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.RequestKeyConstants;
import org.example.dto.ActivityDTO;
import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.service.ClubClientService;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

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
    private ClubClientService clubClientService;

    @Autowired
    private ActivitymemberService activitymemberService;

    @GetMapping("/getActivityMemberBySid")
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        return activitymemberService.getActivityMemberBySid(id);
    }

    @GetMapping("/activityInfo")
    public Object activityInfo(Integer id){
        Club clubInfo = clubClientService.getClubInfoById(id);
        return activityService.activityInfo(id, clubInfo);
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
        List<Club> clubList = clubClientService.getClubList(clubIdList);
        System.out.println(clubList);
        IntStream.range(0, activities.size()).forEach(i->activities.get(i).setClubName(clubList.get(i).getClubName()));
        System.out.println(activities);
        return Result.success(new DataVO(ResultCode.GET_ALL_ACTIVITY_INFO, activities));
    }

    @GetMapping("/latestActivities")
    public Object getLatestActivities(Integer pageNumber, Integer pageSize){
        return activityService.getLatestActivities(pageNumber, pageSize);
    }

    @PostMapping("/newActivity")
    public Object addActivity(@RequestHeader(value = RequestKeyConstants.ID) Integer id, @ModelAttribute Activity activity){
        System.out.println(activity);
        return  activityService.addActivity(activity);
    }

    @PostMapping("/personalPerformance")
    public Object personalPerformance(@RequestHeader(value = RequestKeyConstants.ID) Integer id){

        return null;
    }

    @PostMapping("/activityPerformance")
    public Object activityPerformance(@RequestHeader(value = RequestKeyConstants.ID) Integer id, @ModelAttribute Activity activity){
        return activityService.activityPerformance(activity);
    }
}
