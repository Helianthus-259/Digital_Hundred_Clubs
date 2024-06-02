package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.ClubDTO;
import org.example.entity.Clubapplicationrecord;
import org.example.entity.Clubmember;
import com.szbt.clubserver.dao.mapper.ClubmemberMapper;
import com.szbt.clubserver.service.ClubmemberService;
import org.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubmember】的数据库操作Service实现
* @createDate 2024-05-31 19:40:13
*/
@Service
public class ClubmemberServiceImpl extends ServiceImpl<ClubmemberMapper, Clubmember>
    implements ClubmemberService{

    @Autowired
    ClubmemberMapper clubmemberMapper;
    @Override
    public List<ClubDTO> queryAllClubMemberBySid(Integer id) {

        MPJLambdaWrapper<Clubmember> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Clubmember.class)
                .select(Club::getClubName, Club::getClubStatus)
                .select(Clubapplicationrecord::getCollegeReviewStatus, Clubapplicationrecord::getUniversityStudentUnionReviewStatus,
                        Clubapplicationrecord::getCollegeReviewOpinion, Clubapplicationrecord::getUniversityStudentUnionReviewOpinion)
                .leftJoin(Club.class, Club::getClubId, Clubmember::getClubId)
                .leftJoin(Clubapplicationrecord.class, Clubapplicationrecord::getClubId, Club::getClubId)
                .eq(Clubmember::getStudentId, id)
                .eq(Club::getClubStatus, 1);

        return clubmemberMapper.selectJoinList(ClubDTO.class, wrapper);
    }
}




