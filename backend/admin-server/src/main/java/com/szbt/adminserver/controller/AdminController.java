package com.szbt.adminserver.controller;

import com.szbt.adminserver.service.AdministratorService;
import com.szbt.adminserver.service.BackboneevaluationService;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.RequestKeyConstants;
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

    @GetMapping("/backBoneEvaluations")
    private Object queryAllBackBoneEvaluations()
    {
        return backboneevaluationService.queryAllBackBoneEvaluations();
    }
}