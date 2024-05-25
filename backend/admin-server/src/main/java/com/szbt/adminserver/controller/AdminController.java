package com.szbt.adminserver.controller;

import com.szbt.adminserver.service.AdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class AdminController {
    @Autowired
    private AdministratorService administratorService;
}