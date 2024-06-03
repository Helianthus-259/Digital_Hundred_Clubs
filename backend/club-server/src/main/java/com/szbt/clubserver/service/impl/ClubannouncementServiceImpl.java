package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.ActivityEffectDTO;
import org.example.dto.ActivityShowDTO;
import org.example.dto.NoticeDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.entity.Clubannouncement;
import com.szbt.clubserver.dao.mapper.ClubannouncementMapper;
import com.szbt.clubserver.service.ClubannouncementService;
import org.example.enums.ResultCode;
import org.example.util.Result;
import org.example.vo.ClubActAndNtcVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubannouncement】的数据库操作Service实现
* @createDate 2024-06-03 12:23:30
*/
@Service
public class ClubannouncementServiceImpl extends ServiceImpl<ClubannouncementMapper, Clubannouncement>
    implements ClubannouncementService{

    @Autowired
    ClubannouncementMapper clubannouncementMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Object queryClubActAndNtc(Integer clubId, List<Activity> activityList) {
        MPJLambdaWrapper<Clubannouncement> wrapper = new MPJLambdaWrapper<Clubannouncement>();
        wrapper.selectAll(Clubannouncement.class)
                .eq(Clubannouncement::getClubId,clubId);
        List<NoticeDTO> noticeDTOList = clubannouncementMapper.selectJoinList(NoticeDTO.class, wrapper);
        List<ActivityShowDTO> activityShowDTOList = modelMapper.map(activityList, new TypeToken<List<ActivityShowDTO>>() {}.getType());
        ClubActAndNtcVO clubActAndNtcVO = new ClubActAndNtcVO(ResultCode.GET_SINGLE_CLUB_ACTIVITY_NOTICE,clubId,activityShowDTOList,noticeDTOList);
        return Result.success(clubActAndNtcVO);
    }
}




