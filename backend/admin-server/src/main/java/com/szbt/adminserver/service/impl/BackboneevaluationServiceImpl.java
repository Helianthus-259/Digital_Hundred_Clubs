package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.BackBoneEvaluationDTO;
import org.example.dto.ClubDTO;
import org.example.entity.*;
import com.szbt.adminserver.dao.mapper.BackboneevaluationMapper;
import com.szbt.adminserver.service.BackboneevaluationService;
import org.example.enums.ResultCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return Result.success(new DataVO(ResultCode.GET_ALL_BACKBONE_EVALUATION,
                backboneevaluationMapper.selectJoinList(BackBoneEvaluationDTO.class,wrapper)));
    }
}




