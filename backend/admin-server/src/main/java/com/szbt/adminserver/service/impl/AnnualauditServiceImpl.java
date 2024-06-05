package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.AnnualAuditDTO;
import org.example.dto.SpecialClubAnnualDTO;
import org.example.dto.SpecialClubBackboneDTO;
import org.example.entity.*;
import com.szbt.adminserver.dao.mapper.AnnualauditMapper;
import com.szbt.adminserver.service.AnnualauditService;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        try{
            List<AnnualAuditDTO> annualAuditDTOS = annualauditMapper.selectJoinList(AnnualAuditDTO.class, wrapper);
            return Result.success(new DataVO(ResultCode.GET_ALL_CLUB_ANNUAL,annualAuditDTOS));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_ANNUAL_ERROR,new SendMsg("获取全部社团年审信息失败"));
        }
    }

    @Override
    public Object queryMyClubAnnualExamData(Integer clubId) {
        MPJLambdaWrapper<Annualaudit> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Annualaudit.class)
                .select(Club::getClubName)
                .leftJoin(Club.class, Club::getClubId, Backboneevaluation::getClubId)
                .eq(Backboneevaluation::getClubId,clubId);
        try{
            List<SpecialClubAnnualDTO> specialClubAnnualDTOS = annualauditMapper.selectJoinList(SpecialClubAnnualDTO.class, wrapper);
            //创建返回对象
            HashMap<String,Object> result = new HashMap<>();
            result.put("code", ResultCode.GET_SPECIAL_CLUB_ANNUAL.getCode());
            result.put("returnData",specialClubAnnualDTOS);
            return Result.success(result);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_SINGLE_CLUB_ANNUAL_ERROR,new SendMsg("获取指定社团年审信息失败"));
        }
    }
}




