package org.example.service;

import org.example.dto.ClubDTO;
import org.example.entity.Club;
import org.example.entity.Clubmember;
import org.example.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "club-server")
public interface ClubClientService {
    @GetMapping("/club/clubsinfo")
    public Object queryAllClubs();

    @GetMapping("/club/getClubInfoBySId")
    public List<ClubDTO> getClubInfoBySId(@RequestParam("id") Integer id);

    @GetMapping("/club/queryClubMemberInfo")
    public Clubmember queryClubMemberInfo(@RequestParam("id") Integer id);

    @GetMapping("/club/getClubInfoById")
    public Club getClubInfoById(@RequestParam("id") Integer id);

    @GetMapping("/club/getClubList")
    public List<Club> getClubList(@RequestParam List<Integer> idList);

    @GetMapping("/club/getAdminClubInfo")
    public List<Club> queryAdminClubInfo(@RequestParam("adminId") Integer adminId);

    @PostMapping("/club/passClubApply")
    public boolean passClubApply(@RequestParam("clubId") Integer clubId);

    @PostMapping("/club/unPassClubApply")
    public boolean unPassClubApply(@RequestParam("clubId") Integer clubId);

    @DeleteMapping("/club/deleteClubRedisData")
    public boolean deleteClubRedisData(@RequestParam("key") String key);
}
