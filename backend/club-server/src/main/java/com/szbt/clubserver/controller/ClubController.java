package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.ClubService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/club")
@Slf4j
public class ClubController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/clubsinfo")
    public Object queryAllClubs(String email, String password)
    {
        return clubService.queryAllClubs();
    }
    public Object queryClubsByName(String name){return clubService.queryClubsByName(name);}
    public Object queryUnpassClubs(){return clubService.queryUnpassClubs();}
    public Object queryPassClubs(){return  clubService.queryPassClubs();}

}
