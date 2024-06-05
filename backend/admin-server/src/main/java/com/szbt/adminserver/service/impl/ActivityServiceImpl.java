package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.adminserver.dao.mapper.ActivityMapper;
import com.szbt.adminserver.service.ActivityService;
import org.example.constants.RequestKeyConstants;
import org.example.dto.ActivityDTO;
import org.example.dto.ClubActivityListDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;
import java.util.stream.IntStream;

import static org.example.enums.IsActivityStarted.*;

/**
* @author 小壳儿
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2024-05-25 21:55:21
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{
    @Autowired
    ActivityMapper activityMapper;


    @Override
    public Object passActivityApproval(Activity activity) {
        activity.setActivityStatus(1);
        try{
            int updateById = activityMapper.updateById(activity);
            if(updateById<=0) return Result.send(StatusCode.PASS_ACTIVITY_APPROVAL_ERROR,new SendMsg("通过活动申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.PASS_ACTIVITY_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.PASS_ACTIVITY_APPROVAL_ERROR,new SendMsg("通过活动申请失败"));
        }
    }

    @Override
    public Object unPassActivityApproval(Activity activity) {
        activity.setActivityStatus(0);
        try{
            int updateById = activityMapper.updateById(activity);
            if(updateById<=0) return Result.send(StatusCode.UN_PASS_ACTIVITY_APPROVAL_ERROR,new SendMsg("否决活动申请失败"));
            return Result.success(new SingleCodeVO(ResultCode.UN_PASS_ACTIVITY_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.UN_PASS_ACTIVITY_APPROVAL_ERROR,new SendMsg("否决活动申请失败"));
        }
    }
}




