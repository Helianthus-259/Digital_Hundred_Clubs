package com.szbt.adminserver.controller;

import com.szbt.adminserver.service.*;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.*;
import org.example.service.ClubClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class AdminController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private BackboneevaluationService backboneevaluationService;

    @Autowired
    private AnnualauditService annualauditService;
    @Autowired
    private StudentclubevaluationService studentclubevaluationService;
    @Autowired
    private ClubClientService clubClientService;
    @Autowired
    private ClubapplicationrecordService clubapplicationrecordService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/backBoneEvaluations")
    private Object queryAllBackBoneEvaluations()
    {
        return backboneevaluationService.queryAllBackBoneEvaluations();
    }

    @GetMapping("/clubAnnuals")
    private Object queryAllClubAnnuals(){return annualauditService.queryAllClubAnnuals();}

    @GetMapping("/clubEvaluations")
    private Object queryAllClubEvaluations()
    {
        return studentclubevaluationService.queryAllClubEvaluations();
    }

    @PostMapping("/adminInfoUpdate")
    private Object updateAdminInfo(@ModelAttribute Administrator administrator) {return administratorService.updateAdminInfo(administrator);}

    @GetMapping("/getAdminInfo")
    private Object getAdminInfo(Integer adminId) {return administratorService.getAdminInfo(adminId);}

    @GetMapping("/myClubBackboneExamData")
    private Object queryMyClubBackboneExamData(Integer clubId)
    {
        return backboneevaluationService.queryMyClubBackboneExamData(clubId);
    }

    @GetMapping("/myClubAnnualExamData")
    private Object queryMyClubAnnualExamData(Integer clubId)
    {
        return annualauditService.queryMyClubAnnualExamData(clubId);
    }

    @GetMapping("/adminInfo")
    public Object getAdmin(Integer adminId){
        Club club = clubClientService.queryAdminClubInfo(adminId);
        return administratorService.getAdmin(adminId,club);
    }

    @PostMapping("/passClubApproval")
    public Object passClubApproval(@ModelAttribute Clubapplicationrecord clubapplicationrecord) {
        return clubapplicationrecordService.passClubApproval(clubapplicationrecord);
    }

    @PostMapping("/unPassClubApproval")
    public Object unPassClubApproval(@ModelAttribute Clubapplicationrecord clubapplicationrecord) {
        return clubapplicationrecordService.unPassClubApproval(clubapplicationrecord);
    }

    @PostMapping("/passActivityApproval")
    public Object passActivityApproval(@ModelAttribute Activity activity) {
        return activityService.passActivityApproval(activity);
    }

    @PostMapping("/unPassActivityApproval")
    public Object unPassActivityApproval(@ModelAttribute Activity activity) {
        return activityService.unPassActivityApproval(activity);
    }

    @PostMapping("/passBackboneAwardsReview")
    public Object passBackboneAwardsReview(@ModelAttribute Backboneevaluation backboneevaluation) {
        return backboneevaluationService.passBackboneAwardsReview(backboneevaluation);
    }

    @PostMapping("/unPassBackboneAwardsReview")
    public Object unPassBackboneAwardsReview(@ModelAttribute Backboneevaluation backboneevaluation) {
        return backboneevaluationService.unPassBackboneAwardsReview(backboneevaluation);
    }

    @PostMapping("/passClubAnnualReview")
    public Object passClubAnnualReview(@ModelAttribute Annualaudit annualaudit) {
        return annualauditService.passClubAnnualReview(annualaudit);
    }

    @PostMapping("/unPassClubAnnualReview")
    public Object unPassClubAnnualReview(@ModelAttribute Annualaudit annualaudit) {
        return annualauditService.unPassClubAnnualReview(annualaudit);
    }

    @PostMapping("/passClubAwardReview")
    public Object passClubAwardReview(@ModelAttribute Studentclubevaluation studentclubevaluation) {
        return studentclubevaluationService.passClubAwardReview(studentclubevaluation);
    }

    @PostMapping("/unPassClubAwardReview")
    public Object unPassClubAwardReview(@ModelAttribute Studentclubevaluation studentclubevaluation) {
        return studentclubevaluationService.unPassClubAwardReview(studentclubevaluation);
    }
}