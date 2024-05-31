package com.szbt.clubserver.controller;

import com.szbt.clubserver.service.ClubService;
import com.szbt.clubserver.service.ClubmemberService;
import org.example.dto.ClubDTO;
import org.example.entity.Club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @GetMapping("/clubsInfo")
    public Object queryAllClubs(String email, String password)
    {
        return clubService.queryAllClubs();
    }
    @PostMapping("/clubName")
    public Object queryClubsByName(String name){return clubService.queryClubsByName(name);}
    @GetMapping("/unpass")
    public Object queryUnpassClubs(){return clubService.queryUnpassClubs();}
    @GetMapping("/pass")
    public Object queryPassClubs(){return  clubService.queryPassClubs();}
    @PostMapping("/add")
    public Object addClub(Club club){return clubService.save(club);}
    @PostMapping("/update")
    public Object updateClub(Club club){return clubService.saveBatch((Collection<Club>) club);}
    @DeleteMapping("/delete")
    public Object deleteClub(String id){return clubService.removeById(id);}

    @GetMapping("/getClubInfoBySId")
    public List<ClubDTO> getClubInfoBySId(Integer id){return clubService.getClubInfoBySId(id);}

    @GetMapping("/tt")
    public Object tt(Integer id){return clubmemberService.queryAllClubMemberBySid(id);}
}
