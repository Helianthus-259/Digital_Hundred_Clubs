package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.clubserver.dao.mapper.AnnualauditMapper;
import com.szbt.clubserver.service.AnnualauditService;
import org.example.entity.Annualaudit;
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
* @description 针对表【annualaudit】的数据库操作Service实现
* @createDate 2024-06-02 21:51:03
*/
@Service
public class AnnualauditServiceImpl extends ServiceImpl<AnnualauditMapper, Annualaudit>
    implements AnnualauditService {

    @Autowired
    AnnualauditMapper annualauditMapper;

    @Override
    public Object clubAnnualAuditForm(Annualaudit annualaudit) {
        Calendar calendar = Calendar.getInstance();
        annualaudit.setDeclarationYear(calendar.get(Calendar.YEAR));
        try{
            int inserted = annualauditMapper.insert(annualaudit);
            if (inserted>0) return Result.success(new SingleCodeVO(ResultCode.ADD_CLUB_ANNUAL_AUDIT));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_CLUB_ANNUAL_AUDIT_ERROR, new SendMsg("提交社团年审失败"));
    }
}




