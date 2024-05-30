package com.szbt.clubserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.ClubDTO;
import org.example.entity.Club;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【club】的数据库操作Service
* @createDate 2024-05-03 03:11:18
*/
public interface ClubService extends IService<Club> {
    Object queryAllClubs();

    Object queryClubsByName(String name);

    Object queryUnpassClubs();

    Object queryPassClubs();

    List<ClubDTO> getClubInfoBySId(Integer id);
}
