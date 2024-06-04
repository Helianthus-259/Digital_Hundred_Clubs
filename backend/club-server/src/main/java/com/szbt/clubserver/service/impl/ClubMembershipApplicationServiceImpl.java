package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.ApplyListDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Club;
import org.example.entity.ClubMembershipApplication;
import com.szbt.clubserver.dao.mapper.ClubMembershipApplicationMapper;
import com.szbt.clubserver.service.ClubMembershipApplicationService;
import org.example.entity.Clubapplicationrecord;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.util.Result;
import org.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author 小壳儿
* @description 针对表【club_membership_application】的数据库操作Service实现
* @createDate 2024-06-04 11:24:18
*/
@Service
public class ClubMembershipApplicationServiceImpl extends ServiceImpl<ClubMembershipApplicationMapper, ClubMembershipApplication>
    implements ClubMembershipApplicationService{

    @Autowired
    private ClubMembershipApplicationMapper clubMembershipApplicationMapper;
    @Override
    public Object queryClubApplyList(Integer clubId) {
        MPJLambdaWrapper<ClubMembershipApplication> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(ClubMembershipApplication.class)
                .select(Student::getStName,
                        Student::getEmail,
                        Student::getCollege,
                        Student::getPoliticalStatus)
                .leftJoin(Student.class, Student::getStudentId, ClubMembershipApplication::getStudentId)
                .eq(ClubMembershipApplication::getClubId, clubId);
        List<ApplyListDTO> applyListDTOS = clubMembershipApplicationMapper.selectJoinList(ApplyListDTO.class, wrapper);
        //创建返回对象
        HashMap<String,Object> result = new HashMap<>();
        result.put("code", ResultCode.GET_CLUB_APPLY_LIST.getCode());
        result.put("applyList",applyListDTOS);
        return Result.success(result);
    }
}




