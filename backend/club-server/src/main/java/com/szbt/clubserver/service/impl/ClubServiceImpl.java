package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.service.ClubService;

import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.service.ClubapplicationrecordService;
import com.szbt.clubserver.service.ClubmemberService;
import lombok.val;
import org.example.dto.*;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import org.example.entity.Student;
import org.example.vo.ClubInfosSuccess;
import org.example.util.Result;
import org.example.enums.ResultCode;
import org.example.vo.DataVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @Autowired
    private ClubapplicationrecordService clubapplicationrecordService;

    @Autowired
    private ClubmemberService clubmemberService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Object queryAllClubs() {
        val clubList = clubMapper.selectList(null);
        List<ClubInfos> clubInfos = clubList.stream()
                .map(ClubInfos::mapClubToClubInfo)
                .collect(Collectors.toList());
        System.out.println(new ClubInfosSuccess(ResultCode.GET_CLUB_INFO,clubInfos));
        return Result.success(new ClubInfosSuccess(ResultCode.GET_CLUB_INFO,clubInfos));
    }


    @Override
    public List<ClubDTO> getClubInfoBySId(Integer id) {

        List<ClubDTO> clubDTOS1 = clubmemberService.queryAllClubMemberBySid(id);
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Club::getClubId, Club::getClubName, Club::getClubStatus)
                .select(Clubapplicationrecord::getCollegeReviewStatus,
                        Clubapplicationrecord::getUniversityStudentUnionReviewStatus,
                        Clubapplicationrecord::getCollegeReviewOpinion,
                        Clubapplicationrecord::getUniversityStudentUnionReviewOpinion)
                .leftJoin(Clubapplicationrecord.class, Clubapplicationrecord::getClubId, Club::getClubId)
                .eq(Club::getContactPersonId, id)
                .eq(Club::getClubStatus, 0);
        List<ClubDTO> clubDTOS = clubMapper.selectJoinList(ClubDTO.class, wrapper);
        clubDTOS.addAll(clubDTOS1);
        System.out.println(clubDTOS);
        return clubDTOS;
    }

    @Override
    public Club getClubInfoById(Integer id) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .eq(Club::getClubId,id);
        Club clubInfo = clubMapper.selectJoinOne(Club.class, wrapper);
        System.out.println(clubInfo);
        return clubInfo;
    }


}




