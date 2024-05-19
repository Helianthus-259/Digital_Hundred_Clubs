package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.ClubService;
import org.example.entity.Club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class ClubController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/clubsInfo")
    public Object queryAllClubs(String email, String password)
    {
        return clubService.queryAllClubs();
    }
    @PostMapping("/clubName")
    public Object queryClubsByName(String name){return clubService.queryClubsByName(name);}
    @GetMapping("/club/unpass")
    public Object queryUnpassClubs(){return clubService.queryUnpassClubs();}
    @GetMapping("/club/pass")
    public Object queryPassClubs(){return  clubService.queryPassClubs();}
    @PostMapping("/add/club")
    public Object addClub(Club club){return clubService.save(club);}
    @PostMapping("/update/club")
    public Object updateClub(Club club){return clubService.saveBatch((Collection<Club>) club);}
    @DeleteMapping("/delete/club")
    public Object deleteClub(String id){return clubService.removeById(id);}
}
