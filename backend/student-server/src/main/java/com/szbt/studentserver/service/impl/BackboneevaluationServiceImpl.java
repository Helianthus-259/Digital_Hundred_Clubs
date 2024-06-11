package com.szbt.studentserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Backboneevaluation;
import com.szbt.studentserver.dao.mapper.BackboneevaluationMapper;
import com.szbt.studentserver.service.BackboneevaluationService;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

/**
* @author 小壳儿
* @description 针对表【backboneevaluation】的数据库操作Service实现
* @createDate 2024-05-31 18:36:07
*/
@Service
public class BackboneevaluationServiceImpl extends ServiceImpl<BackboneevaluationMapper, Backboneevaluation>
    implements BackboneevaluationService{

    @Autowired
    BackboneevaluationMapper backboneevaluationMapper;
    @Override
    public Object addBackBoneEvaluate(Backboneevaluation backboneevaluation) {
        int currentYear = Year.now().getValue();
        backboneevaluation.setDeclarationYear(currentYear);
        try{
            int inserted = backboneevaluationMapper.insert(backboneevaluation);
            if (inserted>0) return Result.success(new SingleCodeVO(ResultCode.ADD_BACKBONE_EVALUATION));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_BACKBONE_EVALUATION_ERROR,new SendMsg("提交骨干评优申请失败"));
    }
}




