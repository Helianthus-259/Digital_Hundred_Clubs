package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.clubserver.service.ClubService;

import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.util.Result;
import lombok.val;
import org.example.dto.ClubInfos;
import org.example.entity.Club;
import org.example.vo.ClubInfosSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 小壳儿
* @description 针对表【club】的数据库操作Service实现
* @createDate 2024-05-03 03:11:18
*/
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club>
    implements ClubService {
    @Autowired
    private ClubMapper clubMapper;

    @Override
    public Object queryAllClubs() {
        val clubList = clubMapper.selectList(null);
        List<ClubInfos> clubInfos = clubList.stream()
                .map(ClubInfos::mapClubToClubInfo)
                .collect(Collectors.toList());
        System.out.println(new ClubInfosSuccess(2,clubInfos));
        return Result.success(new ClubInfosSuccess(2,clubInfos));
    }
}




