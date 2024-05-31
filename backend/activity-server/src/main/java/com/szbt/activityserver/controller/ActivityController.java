package com.szbt.activityserver.controller;

import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.service.ActivitymemberService;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ActivityMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ActivitymemberService activitymemberService;

    @GetMapping("/getActivityMemberBySid")
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        return activitymemberService.getActivityMemberBySid(id);
    }
}
