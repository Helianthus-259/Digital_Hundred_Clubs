package org.example.service;

import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "activity-server")
public interface ActivityClientService {
    @GetMapping("/activity/getActivityMemberBySid")
    public List<ActivityMemberDTO> getActivityMemberBySid(@RequestParam("id") Integer id);

    @GetMapping("/activity/queryActivityInfoByClubIdList")
    public List<List<Activity>> queryActivityInfoByClubIdList(@RequestParam List<Integer> idList);
}
