package com.szbt.clubserver.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.clubserver.dao.mapper.StudentclubevaluationMapper;
import com.szbt.clubserver.service.StudentclubevaluationService;
import org.example.entity.Studentclubevaluation;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
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

}




