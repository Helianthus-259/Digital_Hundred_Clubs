package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubMembershipApplicationMapper;
import com.szbt.clubserver.dao.mapper.ClubmemberMapper;
import com.szbt.clubserver.service.ClubMembershipApplicationService;
import org.example.dto.ApplyListDTO;
import org.example.entity.Club;
import org.example.entity.ClubMembershipApplication;
import org.example.entity.Clubmember;
import org.example.entity.Student;
import org.example.enums.Position;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private ClubmemberMapper  clubmemberMapper;
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

    @Override
    public Object joinClub(ClubMembershipApplication clubMembershipApplication) {
        clubMembershipApplication.setCreateTime(new Date());
        int insertById = clubMembershipApplicationMapper.insert(clubMembershipApplication);
        if(insertById<=0) return Result.send(StatusCode.ADD_CLUB_MEMBERSHIP_APPLICATION_ERROR,new SendMsg("申请加入社团失败"));
        return Result.success(new SingleCodeVO(ResultCode.ADD_CLUB_MEMBERSHIP_APPLICATION));
    }

    @Override
    public Object agreeClubApply(Club club, Integer studentId, Boolean ok) {
        if(!ok) return Result.send(StatusCode.AGREE_CLUB_APPLY_ERROR,new SendMsg("同意加入社团申请失败"));
        MPJLambdaWrapper<ClubMembershipApplication>  wrapper = new MPJLambdaWrapper<ClubMembershipApplication>()
                .selectAll(ClubMembershipApplication.class)
                .eq(ClubMembershipApplication::getClubId, club.getClubId())
                .eq(ClubMembershipApplication::getStudentId, studentId);
        ClubMembershipApplication clubMembershipApplication = clubMembershipApplicationMapper.selectOne(wrapper);
        System.out.println(clubMembershipApplication);
        clubMembershipApplication.setStatus(1); // 设置为通过
        int updateById =  clubMembershipApplicationMapper.updateById(clubMembershipApplication);
        if(updateById<=0)  return Result.send(StatusCode.AGREE_CLUB_APPLY_ERROR,new SendMsg("同意加入社团申请失败"));

        // 新增社团成员表
        // 设置数据
        Clubmember clubmember = new Clubmember();
        clubmember.setStudentId(studentId);
        clubmember.setClubId(club.getClubId());
        clubmember.setClubName(club.getClubName());
        clubmember.setPosition(Position.MEMBER.getCode());
        clubmember.setJoinDate(new Date());
        // 新增
        int insetById = clubmemberMapper.insert(clubmember);
        if(insetById<=0) return Result.send(StatusCode.AGREE_CLUB_APPLY_ERROR,new SendMsg("同意加入社团申请失败"));
        return  Result.success(new DataVO(ResultCode.AGREE_CLUB_APPLY, studentId));
    }

    @Override
    public Object rejectClubApply(Integer clubId, Integer studentId, String rejectReason) {
        MPJLambdaWrapper<ClubMembershipApplication>  wrapper = new MPJLambdaWrapper<ClubMembershipApplication>()
                .selectAll(ClubMembershipApplication.class)
                .eq(ClubMembershipApplication::getClubId, clubId)
                .eq(ClubMembershipApplication::getStudentId, studentId);
        ClubMembershipApplication clubMembershipApplication = clubMembershipApplicationMapper.selectOne(wrapper);
        System.out.println(clubMembershipApplication);
        clubMembershipApplication.setStatus(0); // 设置为未通过
        clubMembershipApplication.setRejectReason(rejectReason);    // 设置拒绝理由
        int updateById =  clubMembershipApplicationMapper.updateById(clubMembershipApplication);
        if(updateById<=0)  return Result.send(StatusCode.REJECT_CLUB_APPLY_ERROR,new SendMsg("拒绝加入社团申请失败"));
        return  Result.success(new DataVO(ResultCode.REJECT_CLUB_APPLY, studentId));
    }
}




