package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.clubserver.dao.mapper.ClubmeetingMapper;
import com.szbt.clubserver.service.ClubmeetingService;
import org.example.entity.Clubmeeting;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        int inserted = clubmeetingMapper.insert(clubmeeting);
        if (inserted<=0) return Result.send(StatusCode.ADD_MEETING_ERROR,new SendMsg("提交新会议失败"));
        return Result.success(new SingleCodeVO(ResultCode.ADD_MEETING));
    }
}




