package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.ClubDTO;
import org.example.entity.Clubmember;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubmember】的数据库操作Service
* @createDate 2024-05-31 19:40:13
*/
public interface ClubmemberService extends IService<Clubmember> {
    List<ClubDTO> queryAllClubMemberBySid(Integer id);

    Object queryClubMemberByClubId(Integer clubId);
}
