package com.szbt.activityserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.ActivityDTO;
import org.example.entity.Activity;
import org.example.entity.Club;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【activity】的数据库操作Service
* @createDate 2024-05-25 21:55:21
*/
public interface ActivityService extends IService<Activity> {
    Object activityInfo(Integer id);

    List<List<Activity>> queryActivityInfoByClubIdList(List<Integer> idList);

    List<ActivityDTO> activitiesInfo();

    Object getLatestActivities(Integer pageNumber, Integer pageSize);

    Object addActivity(Activity activity);

    Object activityPerformance(Activity activity);

    Object queryClubActivityList(Integer clubId,Integer pageNumber, Integer pageSize);

    Object getActivitiesInfo(List<ActivityDTO> activities, List<Integer> clubIdList);
}
