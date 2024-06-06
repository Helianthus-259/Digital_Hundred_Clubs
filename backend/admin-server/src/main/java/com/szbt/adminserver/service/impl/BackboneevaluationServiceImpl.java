package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.adminserver.dao.mapper.BackboneevaluationMapper;
import com.szbt.adminserver.service.BackboneevaluationService;
import org.example.dto.BackBoneEvaluationDTO;
import org.example.dto.SpecialClubBackboneDTO;
import org.example.entity.Backboneevaluation;
import org.example.entity.Clubmember;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author 小壳儿
* @description 针对表【backboneevaluation】的数据库操作Service实现
* @createDate 2024-05-31 23:57:41
*/
@Service
public class BackboneevaluationServiceImpl extends ServiceImpl<BackboneevaluationMapper, Backboneevaluation>
    implements BackboneevaluationService{
    @Autowired
    BackboneevaluationMapper backboneevaluationMapper;
    @Override
    public Object queryAllBackBoneEvaluations() {
        MPJLambdaWrapper<Backboneevaluation> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Backboneevaluation.class)
                .select(Clubmember::getClubName, Clubmember::getPosition)
                .select(Student::getCollege, Student::getContact,
                        Student::getStudentNumber, Student::getStName,Student::getPoliticalStatus)
                .leftJoin(Student.class, Student::getStudentId, Backboneevaluation::getStudentId)
                .leftJoin(Clubmember.class, Clubmember::getClubId, Backboneevaluation::getClubId)
                .eq(Clubmember::getStudentId, Backboneevaluation::getStudentId);
        try{
            List<BackBoneEvaluationDTO> backBoneEvaluationDTOS = backboneevaluationMapper.selectJoinList(BackBoneEvaluationDTO.class, wrapper);
            return Result.success(new DataVO(ResultCode.GET_ALL_BACKBONE_EVALUATION, backBoneEvaluationDTOS));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_BACKBONE_EVALUATION_ERROR,new SendMsg("获取骨干评优信息失败"));
        }
    }

    @Override

    public Object queryMyClubBackboneExamData(Integer clubId) {
        MPJLambdaWrapper<Backboneevaluation> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Backboneevaluation.class)
                .select(Student::getStName)
                .leftJoin(Student.class, Student::getStudentId, Backboneevaluation::getStudentId)
                .eq(Backboneevaluation::getClubId,clubId);
        try{
            List<SpecialClubBackboneDTO> specialClubBackboneDTOS = backboneevaluationMapper.selectJoinList(SpecialClubBackboneDTO.class, wrapper);
            //创建返回对象
            HashMap<String,Object> result = new HashMap<>();
            result.put("code", ResultCode.GET_SPECIAL_CLUB_BACKBONE.getCode());
            result.put("returnData",specialClubBackboneDTOS);
            return Result.success(result);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_SINGLE_CLUB_BACK_BONE_ERROR,new SendMsg("获取指定社团骨干信息失败"));
        }
    }

    @Override
    public Object passBackboneAwardsReview(Backboneevaluation backboneevaluation) {
        backboneevaluation.setStatus(1);
        try{
            int updateById = backboneevaluationMapper.updateById(backboneevaluation);
            if(updateById<=0) return Result.send(StatusCode.PASS_CLUB_BACK_BONE_APPROVAL_ERROR,new SendMsg("通过骨干申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.PASS_CLUB_BACK_BONE_APPROVAL));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.PASS_CLUB_BACK_BONE_APPROVAL_ERROR,new SendMsg("通过骨干申请失败"));
        }
    }

    @Override
    public Object unPassBackboneAwardsReview(Backboneevaluation backboneevaluation) {
        backboneevaluation.setStatus(0);
        try{
            int updateById = backboneevaluationMapper.updateById(backboneevaluation);
            if(updateById<=0) return Result.send(StatusCode.PASS_CLUB_BACK_BONE_APPROVAL_ERROR,new SendMsg("否决骨干申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.PASS_CLUB_BACK_BONE_APPROVAL));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.PASS_CLUB_BACK_BONE_APPROVAL_ERROR,new SendMsg("否决骨干申请失败"));
        }
    }
}




