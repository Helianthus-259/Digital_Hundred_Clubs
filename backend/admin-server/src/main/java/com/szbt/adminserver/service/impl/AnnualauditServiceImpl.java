package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.AnnualAuditDTO;
import org.example.entity.*;
import com.szbt.adminserver.dao.mapper.AnnualauditMapper;
import com.szbt.adminserver.service.AnnualauditService;
import org.example.enums.ResultCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【annualaudit】的数据库操作Service实现
* @createDate 2024-06-02 21:51:03
*/
@Service
public class AnnualauditServiceImpl extends ServiceImpl<AnnualauditMapper, Annualaudit>
    implements AnnualauditService{

    @Autowired
    AnnualauditMapper annualauditMapper;

    @Override
    public Object queryAllClubAnnuals() {
        MPJLambdaWrapper<Annualaudit> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Annualaudit.class)
                .select(Club::getClubName)
                .leftJoin(Club.class, Club::getClubId, Annualaudit::getClubId);
        List<AnnualAuditDTO> annualAuditDTOS = annualauditMapper.selectJoinList(AnnualAuditDTO.class, wrapper);
        return Result.success(new DataVO(ResultCode.GET_ALL_CLUB_ANNUAL,annualAuditDTOS));
    }
}




