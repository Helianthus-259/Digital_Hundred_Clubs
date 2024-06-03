package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Activity;
import org.example.entity.Clubannouncement;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubannouncement】的数据库操作Service
* @createDate 2024-06-03 12:23:30
*/
public interface ClubannouncementService extends IService<Clubannouncement> {

    Object queryClubActAndNtc(Integer clubId, List<Activity> activityList);

}
