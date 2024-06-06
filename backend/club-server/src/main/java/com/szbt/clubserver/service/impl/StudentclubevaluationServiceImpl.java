package com.szbt.clubserver.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.StudentclubevaluationMapper;
import com.szbt.clubserver.service.StudentclubevaluationService;
import org.example.dto.SingleClubEvaluationDTO;
import org.example.entity.*;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
* @author 小壳儿
* @description 针对表【studentclubevaluation】的数据库操作Service实现
* @createDate 2024-06-02 23:31:05
*/
@Service
public class StudentclubevaluationServiceImpl extends ServiceImpl<StudentclubevaluationMapper, Studentclubevaluation>
    implements StudentclubevaluationService {

    @Autowired
    private  StudentclubevaluationMapper studentclubevaluationMapper;
    @Override
    public Object clubEvaluationForm(Studentclubevaluation studentclubevaluation) {
        Calendar calendar = Calendar.getInstance();
        studentclubevaluation.setDeclarationYear(calendar.get(Calendar.YEAR));
        int inserted = studentclubevaluationMapper.insert(studentclubevaluation);
        if (inserted<=0) return Result.send(StatusCode.ADD_CLUB_EVALUATION_ERROR, new SendMsg("提交社团评优失败"));
        return Result.success(new SingleCodeVO(ResultCode.ADD_CLUB_EVALUATION));
    }

    @Override
    public Object clubEvaluateInfo(Integer clubId, Club clubInfo, Student studentInfo, String department) {
        return null;
    }

    @Override
    public Object queryClubEvaluationInfo(Integer recordId) {
        MPJLambdaWrapper<Studentclubevaluation> wrapper = new MPJLambdaWrapper<Studentclubevaluation>()
                .selectAll(Studentclubevaluation.class)
                .select(Club::getAdministrativeGuideTeacherName,
                        Club::getBusinessGuideTeacherName,
                        Club::getIsFinancialInformationPublic,
                        Club::getTotalMembers,Club::getClubName)
                .leftJoin(Club.class,Club::getClubId,Studentclubevaluation::getClubId)
                .eq(Studentclubevaluation::getRecordId,recordId);
        try{
            SingleClubEvaluationDTO singleClubEvaluationDTO = studentclubevaluationMapper.selectJoinOne(SingleClubEvaluationDTO.class, wrapper);
            return Result.success(new DataVO(ResultCode.GET_CLUB_EVALUATION,singleClubEvaluationDTO));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_EVALUATION_ERROR,new SendMsg("获取详细社团评优信息失败"));
        }
    }

}




