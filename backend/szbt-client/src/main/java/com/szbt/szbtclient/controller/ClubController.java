package com.szbt.szbtclient.controller;

import org.example.service.ClubClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubController {
    @Autowired
    private ClubClientService clubClientService;
    @GetMapping("/api/clubsinfo")
    public Object queryAllClubs()
    {
        return clubClientService.queryAllClubs();
    }

}
