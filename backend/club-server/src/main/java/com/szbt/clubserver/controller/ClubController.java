package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.*;
import org.example.dto.ClubDTO;
import org.example.entity.*;
import org.example.service.ActivityClientService;
import org.example.service.AdminClientService;
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
    private ClubmeetingService clubmeetingService;
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
    @Autowired
    private  ClubawardsService  clubawardsService;
    @Autowired
    private AdminClientService  adminClientService;
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
        return clubMembershipApplicationService.rejectClubApply(clubId,studentId,rejectReason);
    }

    @PostMapping("/agreeClubApply")
    public  Object agreeClubApply(Integer clubId, Integer studentId){
        Club club = clubService.getClubInfoById(clubId);
        Boolean ok = clubService.addClubTotalMember(clubId);
        return clubMembershipApplicationService.agreeClubApply(club,studentId,ok);
    }

    @PostMapping("/joinClub")
    public Object joinClub(@ModelAttribute ClubMembershipApplication clubMembershipApplication){
        System.out.println(clubMembershipApplication);
        return clubMembershipApplicationService.joinClub(clubMembershipApplication);
    }

    @GetMapping("/meetings")
    public Object meetings(Integer clubId){
        return clubmeetingService.meetings(clubId);
    }

    @GetMapping("/associationAwards")
    public Object associationAwards(Integer clubId){
        return clubawardsService.associationAwards(clubId);
    }

    @GetMapping("/clubNoticeList")
    public Object clubNoticeList(Integer clubId){
        return clubannouncementService.clubNoticeList(clubId);
    }

    @GetMapping("/topTenClubs")//   暂时使用人数数量来进行top10排序
    public Object topTenClubs(){
        return clubService.topTenClubs();
    }

    @GetMapping("/clubEvaluateInfo")// 未实现
    public Object clubEvaluateInfo(Integer clubId){
        Club clubInfo =  clubService.getClubInfoById(clubId);
        Student studentInfo = studentClientService.queryStudentInfo(clubInfo.getContactPersonId());
        String responsibleDepartment = adminClientService.getAdminInfo(clubInfo.getResponsibleDepartmentId());
        return studentclubevaluationService.clubEvaluateInfo(clubId, clubInfo, studentInfo,responsibleDepartment);
    }

    @GetMapping("/clubApplicationInfo")
    public Object queryClubApplicationInfo(Integer clubId)
    {
        return clubService.queryClubApplicationInfo(clubId);
    }

    @GetMapping("/getClubAnnual")
    public Object queryClubAnnualInfo(Integer declarationId){return clubService.queryClubAnnualInfo(declarationId);}

    @GetMapping("/getClubAward")
    public Object queryClubEvaluationInfo(Integer recordId){return studentclubevaluationService.queryClubEvaluationInfo(recordId);}

    @GetMapping("/clubInfo")
    public Object queryClubInfoInfo(Integer clubId){return clubService.queryClubInfoInfo(clubId);}
}
