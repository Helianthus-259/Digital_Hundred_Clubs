package com.szbt.activityserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.constants.RequestKeyConstants;
import org.example.dto.ActivityDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Activity;
import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.dao.mapper.ActivityMapper;
import org.example.entity.Club;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Object activityInfo(@RequestHeader(value = RequestKeyConstants.ID) Integer id, Club clubInfo) {
        System.out.println(id);
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class)
                .eq(Activity::getActivityId,id);
        ActivityDTO activity = activityMapper.selectJoinOne(ActivityDTO.class, wrapper);
        activity.setClubName(clubInfo.getClubName());
        System.out.println(activity);
        return Result.success(new DataVO(ResultCode.GET_ACTIVITY_INFO, activity));
    }

    @Override
    public List<List<Activity>> queryActivityInfoByClubIdList(List<Integer> idList) {
        List<List<Activity>> result = new ArrayList<>();

        idList.forEach(clubId -> {
            MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                    .selectAll(Activity.class)
                    .eq(Activity::getClubId, clubId);

            List<Activity> activityList = activityMapper.selectJoinList(Activity.class, wrapper);
            result.add(activityList);
        });

        return result;
    }

    @Override
    public List<ActivityDTO> activitiesInfo() {
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class);
        List<ActivityDTO> activity = activityMapper.selectJoinList(ActivityDTO.class, wrapper);
        System.out.println(activity);
        return activity;
    }

    @Override
    public Object getLatestActivities(Integer pageNumber, Integer pageSize) {
        IPage<Activity> page = new Page<>(pageNumber, pageSize);
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class)
                .orderByDesc(Activity::getActivityPublishTime);
        IPage<Activity>  activity = activityMapper.selectPage(page, wrapper);
        return Result.success(new DataVO(ResultCode.GET_LATEST_ACTIVITY, activity.getRecords()));
    }

    @Override
    public Object addActivity(Activity activity) {
        Date date = new Date();
        activity.setActivityPublishTime(date);
        int inserted = activityMapper.insert(activity);
        if (inserted<=0) return Result.send(StatusCode.ADD_ACTIVITY_ERROR,new SendMsg("提交新活动失败"));
        return Result.success(new SingleCodeVO(ResultCode.ADD_ACTIVITY));
    }

    @Override
    public Object activityPerformance(Activity activity) {
        int updateById = activityMapper.updateById(activity);
        if (updateById<=0) return Result.send(StatusCode.ADD_ACTIVITY_PERFORMANCE_ERROR,new SendMsg("更新社团活动成效"));
        return Result.success(new SingleCodeVO(ResultCode.ADD_activityPerformance));
    }

}




