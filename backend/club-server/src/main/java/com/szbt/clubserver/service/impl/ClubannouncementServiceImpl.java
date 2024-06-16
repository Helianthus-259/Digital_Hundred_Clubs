package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubannouncementMapper;
import com.szbt.clubserver.service.ClubannouncementService;
import org.example.dto.ActivityShowDTO;
import org.example.dto.NoticeDTO;
import org.example.entity.Activity;
import org.example.entity.Clubannouncement;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.ActivityClientService;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.Result;
import org.example.vo.ClubActAndNtcVO;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

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

    @Autowired
    private ActivityClientService activityClientService;

    @Override
    public Object queryClubActAndNtc(Integer clubId) {
        MPJLambdaWrapper<Clubannouncement> wrapper = new MPJLambdaWrapper<Clubannouncement>();
        wrapper.selectAll(Clubannouncement.class)
                .eq(Clubannouncement::getClubId,clubId);
        List<Integer> idList = new ArrayList<>();
        idList.add(clubId);
        try {
            List<NoticeDTO> noticeDTOList = clubannouncementMapper.selectJoinList(NoticeDTO.class, wrapper);
            //处理文件请求
            IntStream.range(0, noticeDTOList.size())
                    .forEach(i -> {
                        String imageUrl = noticeDTOList.get(i).getImageUrl();
                        noticeDTOList.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
                    });
            List<List<Activity>> activityInfoLists = activityClientService.queryActivityInfoByClubIdList(idList);
            List<ActivityShowDTO> activityShowDTOList = modelMapper.map(activityInfoLists.get(0), new TypeToken<List<ActivityShowDTO>>() {}.getType());
            for(ActivityShowDTO act : activityShowDTOList){
                act.setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(act.getImageUrl()));
            }
            ClubActAndNtcVO clubActAndNtcVO = new ClubActAndNtcVO(ResultCode.GET_SINGLE_CLUB_ACTIVITY_NOTICE,clubId,activityShowDTOList,noticeDTOList);
            return Result.success(clubActAndNtcVO);
        } catch (Exception e) {
            String exceptionAsString = e.toString();
            return Result.send(StatusCode.GET_CLUB_ACT_AND_NTC_ERROR,new SendMsg(exceptionAsString));
        }
    }

    @Override
    public Object newNotice(Clubannouncement clubannouncement) {
        Date date = new Date();
        clubannouncement.setPublishTime(date);
        try{
            int inserted = clubannouncementMapper.insert(clubannouncement);
            if (inserted>0) return Result.success(new SingleCodeVO(ResultCode.ADD_NOTICE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_NOTICE_ERROR, new SendMsg("发布新通知失败"));
    }

    @Override
    public Object clubNoticeList(Integer clubId) {
        MPJLambdaWrapper<Clubannouncement>  wrapper = new MPJLambdaWrapper<Clubannouncement>()
                .selectAll(Clubannouncement.class)
                .eq(Clubannouncement::getClubId,clubId);
        try{
            List<Clubannouncement> clubannouncementList = clubannouncementMapper.selectJoinList(Clubannouncement.class,wrapper);
            return Result.success(new DataVO(ResultCode.GET_CLUB_NOTICE_LIST,  clubannouncementList));
        }catch (Exception e){
            String exceptionAsString = e.toString();
            return Result.send(StatusCode.GET_CLUB_NOTICE_LIST_ERROR,new SendMsg(exceptionAsString));
        }
    }


}




