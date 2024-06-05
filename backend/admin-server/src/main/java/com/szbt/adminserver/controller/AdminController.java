package com.szbt.adminserver.controller;

import com.szbt.adminserver.service.*;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Administrator;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
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
}