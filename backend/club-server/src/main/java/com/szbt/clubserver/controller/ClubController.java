package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.*;
import org.example.dto.ClubDTO;
import org.example.entity.*;
import org.example.service.ActivityClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private StudentClientService  studentClientService;
    @Autowired
    private ClubapplicationrecordService clubapplicationrecordService;
    @Autowired
    private ClubMembershipApplicationService clubMembershipApplicationService;
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

    @GetMapping("/clubMembers")
    public Object queryClubMemberByClubId(@RequestParam("clubId") Integer clubId)
    {
        return clubmemberService.queryClubMemberByClubId(clubId);
    }

    @GetMapping("/clubApplyList")
    public  Object queryClubApplyList(Integer clubId)
    {
        return clubMembershipApplicationService.queryClubApplyList(clubId);
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

    @PostMapping("/updateClubInfo")
    public Object updateClubInfo(@ModelAttribute Club club){
        return clubService.updateClubInfo(club);
    }

    @PostMapping("/addClubMember")
    public Object addClubMember(@ModelAttribute Clubmember clubmember){
        return clubmemberService.addClubMember(clubmember);
    }
    @PostMapping("/updateClubMember")
    public Object updateClubMember(Integer clubId, String oldStudent, String newStudent){
        return clubmemberService.updateClubMember(clubId,oldStudent,newStudent);
    }

    @PostMapping("/deleteClubMember")
    public Object deleteClubMember(Integer clubId, String studentNumber){
        List<String> number = Collections.singletonList(studentNumber);
        System.out.println(number);
        List<Student> studentList = studentClientService.getStudentByNumber(number);
        Student student = studentList.get(0);
        System.out.println(student);
        return  clubmemberService.deleteClubMember(clubId,student.getStudentId());
    }

    @PostMapping("/newClubApply")
    public Object newClubApply(@ModelAttribute Club club, String advisorResumeAttachmentUrl){
        System.out.println(club);
        return clubapplicationrecordService.newClubApply(club, advisorResumeAttachmentUrl);
    }

    @PostMapping("/rejectClubApply")
    public Object rejectClubApply(Integer clubId, Integer studentId, String rejectReason){
        return null;
    }

    @PostMapping("/agreeClubApply")
    public  Object agreeClubApply(Integer clubId, Integer studentId){
        return null;
    }


}
