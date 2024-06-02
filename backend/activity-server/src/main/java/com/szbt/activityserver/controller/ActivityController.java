package com.szbt.activityserver.controller;

import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.service.ActivitymemberService;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.RequestKeyConstants;
import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.service.ClubClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
