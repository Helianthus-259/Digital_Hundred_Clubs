package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.*;
import org.example.dto.ClubDTO;
import org.example.entity.*;
import org.example.service.ActivityClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubmemberService clubmemberService;
    @Autowired
    private ClubannouncementService clubannouncementService;
    @Autowired
    private ActivityClientService activityClientService;
    @Autowired
    private ClubmeetingService  clubmeetingService;
    @Autowired
    private StudentclubevaluationService  studentclubevaluationService;
    @Autowired
    private AnnualauditService annualauditService;
    @GetMapping("/clubsInfo")
    public Object queryAllClubs(String email, String password)
    {
        return clubService.queryAllClubs();
    }

    @GetMapping("/clubActAndNtc")
    public Object queryClubActAndNtc(@RequestParam("clubId") Integer clubId){
        List<Integer> idList = new ArrayList<>();
        idList.add(clubId);
        List<List<Activity>> activityInfoLists = activityClientService.queryActivityInfoByClubIdList(idList);
        return clubannouncementService.queryClubActAndNtc(clubId,activityInfoLists.get(0));
    }

    @GetMapping("/clubIntroduction")
    public Object queryClubIntroductionByClubId(@RequestParam("clubId") Integer clubId)
    {
        return clubService.queryClubIntroductionByClubId(clubId);
    }

    @GetMapping("/getClubInfoBySId")
    public List<ClubDTO> getClubInfoBySId(Integer id){return clubService.getClubInfoBySId(id);}

    @GetMapping("/tt")
    public Object tt(Integer id){return clubmemberService.queryAllClubMemberBySid(id);}

    @GetMapping("/queryClubMemberInfo")
    public Clubmember queryClubMemberInfo(Integer id){
        return clubmemberService.getById(id);
    }

    @GetMapping("/getClubInfoById")
    public Club getClubInfoById(Integer id){return clubService.getClubInfoById(id);}

    @GetMapping("/getClubList")
    public List<Club> getClubList(@RequestParam List<Integer> idList){
        return clubService.getClubList(idList);}

    @PostMapping("/updateClubDescription")
    public Object updateClubDescription(@ModelAttribute Club club){
        return clubService.updateClubDescription(club);
    }

    @PostMapping("/newMeeting")
    public Object newMeeting(@ModelAttribute Clubmeeting clubmeeting){
        return clubmeetingService.newMeeting(clubmeeting);
    }

    @PostMapping("/newNotice")
    public Object newNotice(@ModelAttribute Clubannouncement clubannouncement){
        return clubannouncementService.newNotice(clubannouncement);
    }

    @PostMapping("/clubEvaluationForm")
    public Object  clubEvaluationForm(@ModelAttribute Studentclubevaluation  studentclubevaluation){
        return studentclubevaluationService.clubEvaluationForm(studentclubevaluation);
    }

    @PostMapping("/clubAnnualAuditForm")
    public Object  clubAnnualAuditForm(@ModelAttribute Annualaudit annualaudit){
        return annualauditService.clubAnnualAuditForm(annualaudit);
    }
}
