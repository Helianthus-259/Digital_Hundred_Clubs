package com.szbt.activityserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.activityserver.dao.mapper.ActivityMapper;
import com.szbt.activityserver.dao.mapper.ActivitymemberMapper;
import com.szbt.activityserver.service.ActivityService;
import org.example.dto.ActivityDTO;
import org.example.dto.ActivityDetailDTO;
import org.example.dto.ClubActivityListDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.ClubClientService;
import org.example.service.StudentClientService;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    ActivitymemberMapper activitymemberMapper;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ClubClientService clubClientService;

    @Autowired
    private StudentClientService  studentClientService;

    @Override
    public Object activityInfo(Integer id) {
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class)
                .eq(Activity::getActivityId,id);
        try{
            ActivityDetailDTO activity = activityMapper.selectJoinOne(ActivityDetailDTO.class, wrapper);
            Club clubInfo = clubClientService.getClubInfoById(activity.getClubId());
            Student student = studentClientService.queryStudentInfo(clubInfo.getContactPersonId());
            activity.setContactPerson(student.getStName());
            activity.setContactPhone(student.getContact());
            activity.setClubName(clubInfo.getClubName());
            activity.setApplicationFormAttachment(FileRequestUrlBuilder.buildFileRequestUrl(activity.getApplicationFormAttachment()));
            activity.setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(activity.getImageUrl()));
            return Result.success(new DataVO(ResultCode.GET_ACTIVITY_INFO, activity));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ACTIVITY_INFO_ERROR,new SendMsg("获取活动信息失败"));
    }

    @Override
    public List<List<Activity>> queryActivityInfoByClubIdList(List<Integer> idList) {
        List<List<Activity>> result = new ArrayList<>();
        try {
            idList.forEach(clubId -> {
                MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                        .selectAll(Activity.class)
                        .eq(Activity::getClubId, clubId);
                List<Activity> activityList = activityMapper.selectJoinList(Activity.class, wrapper);
                //处理文件请求
                IntStream.range(0, activityList.size())
                        .forEach(i -> {
                            String imageUrl = activityList.get(i).getImageUrl();
                            activityList.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
                        });
                result.add(activityList);
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActivityDTO> activitiesInfo() {
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class);
        try{
            List<ActivityDTO> activity = activityMapper.selectJoinList(ActivityDTO.class, wrapper);
            for(ActivityDTO act : activity){
                act.setApplicationFormAttachment(FileRequestUrlBuilder.buildFileRequestUrl(act.getApplicationFormAttachment()));
                act.setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(act.getImageUrl()));
            }
            return activity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getLatestActivities(Integer pageNumber, Integer pageSize) {
        // 创建分页对象
        Page<Activity> page = new Page<>(pageNumber+1, pageSize);
        page.setSearchCount(true);
        // 构建查询条件
        MPJLambdaWrapper<Activity> wrapper = new MPJLambdaWrapper<Activity>()
                .selectAll(Activity.class);
        try{
            // 执行分页查询
            activityMapper.selectPage(page, wrapper);
            // 获取分页结果
            List<Activity> activityList = page.getRecords();
            for(Activity act : activityList){
                act.setApplicationFormAttachment(FileRequestUrlBuilder.buildFileRequestUrl(act.getApplicationFormAttachment()));
                act.setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(act.getImageUrl()));
            }
            return Result.success(new DataVO(ResultCode.GET_LATEST_ACTIVITY, activityList));

        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_LATEST_ACTIVITY_ERROR,new SendMsg("获取最新活动失败"));
    }

    @Override
    public Object addActivity(Activity activity) {
        Date date = new Date();
        activity.setActivityPublishTime(date);
        try{
            int inserted = activityMapper.insert(activity);
            if (inserted > 0) return Result.success(new SingleCodeVO(ResultCode.ADD_ACTIVITY));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_ACTIVITY_ERROR,new SendMsg("提交新活动失败"));
    }

    @Override
    public Object activityPerformance(Activity activity) {
        try{
            int updateById = activityMapper.updateById(activity);
            if (updateById > 0) return Result.success(new SingleCodeVO(ResultCode.ADD_activityPerformance));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_ACTIVITY_PERFORMANCE_ERROR,new SendMsg("更新社团活动成效失败"));
    }

    @Override
    public Object queryClubActivityList(Integer clubId, Integer pageNumber, Integer pageSize) {
        System.out.println(pageNumber+1);
        System.out.println(pageSize);
        // 创建分页对象
        Page<Activity> page = new Page<>(pageNumber+1, pageSize);
        page.setSearchCount(true);
        // 构建查询条件
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", clubId);
        try{
            // 执行分页查询
            activityMapper.selectPage(page, wrapper);
            // 获取分页结果
            List<Activity> activityList = page.getRecords();
            long total = page.getTotal();
            System.out.println(total);
            //映射到DTO
            List<ClubActivityListDTO> clubActivityListDTOS = modelMapper.map(activityList, new TypeToken<List<ClubActivityListDTO>>() {}.getType());
            //处理图片请求,并设置开始状态
            IntStream.range(0, clubActivityListDTOS.size())
                    .forEach(i -> {
                        String imageUrl = clubActivityListDTOS.get(i).getImageUrl();
                        clubActivityListDTOS.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
                        Date currentTime = new Date();
                        Date activityStartTime = activityList.get(i).getActivityStartTime();
                        Date activityEndTime = activityList.get(i).getActivityEndTime();
                        if (currentTime.before(activityStartTime)) {
                            clubActivityListDTOS.get(i).setStatus(NO_STARTED.getCode());
                        } else if (currentTime.after(activityEndTime)) {
                            clubActivityListDTOS.get(i).setStatus(ENDED.getCode());
                        } else {
                            clubActivityListDTOS.get(i).setStatus(STARTING.getCode());
                        }
                    });
            // 构造结果对象
            Map<String, Object> result = new HashMap<>();
            result.put("code", ResultCode.GET_CLUB_ACTIVITY_LIST.getCode());
            result.put("activityList", clubActivityListDTOS);

            return Result.success(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_CLUB_ACTIVITY_LIST_ERROR,new SendMsg("获取社团活动列表失败"));
    }

    @Override
    public Object getActivitiesInfo(List<ActivityDTO> activities, List<Integer> clubIdList) {
        try{
            IntStream.range(0, activities.size()).forEach(i-> {
                Club clubInfo = clubClientService.getClubInfoById(clubIdList.get(i));
                activities.get(i).setClubName(clubInfo.getClubName());
            });
            System.out.println(activities);
            return Result.success(new DataVO(ResultCode.GET_ALL_ACTIVITY_INFO, activities));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ALL_ACTIVITY_INFO_ERROR,new SendMsg("获取所有活动信息失败"));
    }


}




