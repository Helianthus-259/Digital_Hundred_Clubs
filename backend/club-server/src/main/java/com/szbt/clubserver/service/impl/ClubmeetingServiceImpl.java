package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubmeetingMapper;
import com.szbt.clubserver.service.ClubmeetingService;
import org.example.entity.Clubmeeting;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author lingyi01
* @description 针对表【clubmeeting】的数据库操作Service实现
* @createDate 2024-06-03 17:17:21
*/
@Service
public class ClubmeetingServiceImpl extends ServiceImpl<ClubmeetingMapper, Clubmeeting>
    implements ClubmeetingService {
    @Autowired
    private ClubmeetingMapper clubmeetingMapper;
    @Override
    public Object newMeeting(Clubmeeting clubmeeting) {
        try{
            int inserted = clubmeetingMapper.insert(clubmeeting);
            if (inserted>0) return Result.success(new SingleCodeVO(ResultCode.ADD_MEETING));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_MEETING_ERROR,new SendMsg("提交新会议失败"));
    }

    @Override
    public Object meetings(Integer clubId) {
        MPJLambdaWrapper<Clubmeeting>  wrapper = new MPJLambdaWrapper<Clubmeeting>()
                .selectAll(Clubmeeting.class)
                .eq(Clubmeeting::getClubId,clubId);
        try{
            List<Clubmeeting> clubMeetings = clubmeetingMapper.selectList(wrapper);
            return Result.success(new DataVO(ResultCode.GET_CLUB_MEETINGS, clubMeetings));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_MEETINGS_ERROR,new SendMsg("获取社团会议列表失败"));
        }
    }
}




