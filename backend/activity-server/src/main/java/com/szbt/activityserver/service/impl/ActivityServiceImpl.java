package com.szbt.activityserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Activity;
import com.szbt.activityserver.service.ActivityService;
import com.szbt.activityserver.dao.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author 小壳儿
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2024-05-25 21:55:21
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




