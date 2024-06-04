package com.szbt.adminserver.controller;

import com.szbt.adminserver.service.AdministratorService;
import com.szbt.adminserver.service.AnnualauditService;
import com.szbt.adminserver.service.BackboneevaluationService;
import com.szbt.adminserver.service.StudentclubevaluationService;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Administrator;
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

}