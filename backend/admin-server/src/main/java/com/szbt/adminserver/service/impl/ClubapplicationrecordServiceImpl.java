package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.adminserver.dao.mapper.ClubapplicationrecordMapper;
import com.szbt.adminserver.service.ClubapplicationrecordService;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.ClubClientService;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubapplicationrecord】的数据库操作Service实现
* @createDate 2024-05-31 19:19:57
*/
@Service
public class ClubapplicationrecordServiceImpl extends ServiceImpl<ClubapplicationrecordMapper, Clubapplicationrecord>
    implements ClubapplicationrecordService {
    @Autowired
    ClubapplicationrecordMapper clubapplicationrecordMapper;

    @Autowired
    ClubClientService clubClientService;

    @Override
    public Object passClubApproval(Clubapplicationrecord clubapplicationrecord) {
        clubapplicationrecord.setUniversityStudentUnionReviewStatus(1);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
            Clubapplicationrecord clubapplicationrecord1 = clubapplicationrecordMapper.selectById(clubapplicationrecord.getRecordId());
            boolean passed = clubClientService.passClubApply(clubapplicationrecord1.getClubId());
            if(!passed) return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.PASS_CLUB_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
        }
    }

    @Override
    public Object unPassClubApproval(Clubapplicationrecord clubapplicationrecord) {
        clubapplicationrecord.setUniversityStudentUnionReviewStatus(0);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
            Clubapplicationrecord clubapplicationrecord1 = clubapplicationrecordMapper.selectById(clubapplicationrecord.getRecordId());
            boolean passed = clubClientService.unPassClubApply(clubapplicationrecord1.getClubId());
            if(!passed) return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.UN_PASS_CLUB_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
        }
    }
}




