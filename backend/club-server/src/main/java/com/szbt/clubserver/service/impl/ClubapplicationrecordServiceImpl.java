package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.dao.mapper.ClubapplicationrecordMapper;
import com.szbt.clubserver.service.ClubServerRedisService;
import com.szbt.clubserver.service.ClubapplicationrecordService;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.RedisKeyBuilder;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubapplicationrecord】的数据库操作Service实现
* @createDate 2024-05-31 19:19:57
*/
@Service
public class ClubapplicationrecordServiceImpl extends ServiceImpl<ClubapplicationrecordMapper, Clubapplicationrecord>
    implements ClubapplicationrecordService{
    @Autowired
    ClubapplicationrecordMapper clubapplicationrecordMapper;

    @Autowired
    ClubMapper  clubMapper;

    @Autowired
    ClubServerRedisService clubServerRedisService;


    @Override
    public List<Clubapplicationrecord> queryClubApplicationRecordBySId(Integer id) {
        MPJLambdaWrapper<Clubapplicationrecord> wrapper = new MPJLambdaWrapper<Clubapplicationrecord>()
                .selectAll(Clubapplicationrecord.class)
                .eq(Clubapplicationrecord::getStudentId,id);
        try{
            List<Clubapplicationrecord> clubapplicationrecords = clubapplicationrecordMapper.selectJoinList(Clubapplicationrecord.class, wrapper);
            return clubapplicationrecords;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Object newClubApply(Club club, String advisorResumeAttachmentUrl) {
        club.setTotalMembers(1);
        club.setEstablishmentDate(new Date());
        try{
            int insertById = clubMapper.insert(club);
            if(insertById<=0) return Result.send(StatusCode.ADD_CLUB_APPLICATION_ERROR,new SendMsg("申请建立社团失败"));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.ADD_CLUB_APPLICATION_ERROR,new SendMsg("申请建立社团失败"));
        }
        Clubapplicationrecord clubapplicationrecord = new Clubapplicationrecord();
        clubapplicationrecord.setClubId(club.getClubId());
        clubapplicationrecord.setAdvisorResumeAttachmentUrl(advisorResumeAttachmentUrl);
        clubapplicationrecord.setStudentId(club.getContactPersonId());// 设置为联系人id
        try{
            int insertById = clubapplicationrecordMapper.insert(clubapplicationrecord);
            String clubInfosListKey = RedisKeyBuilder.generateClubInfosListKey();
            boolean deleted = clubServerRedisService.deleteFromRedis(clubInfosListKey);
            if(insertById<=0) return Result.send(StatusCode.ADD_CLUB_APPLICATION_ERROR,new SendMsg("申请建立社团失败"));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.ADD_CLUB_APPLICATION_ERROR,new SendMsg("申请建立社团失败"));
        }
        return Result.success(new SingleCodeVO(ResultCode.ADD_CLUB_APPLICATION));
    }
}




