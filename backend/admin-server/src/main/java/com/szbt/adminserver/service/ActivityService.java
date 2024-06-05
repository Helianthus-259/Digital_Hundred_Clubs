package com.szbt.adminserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.ActivityDTO;
import org.example.entity.Activity;
import org.example.entity.Club;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【activity】的数据库操作Service
* @createDate 2024-05-25 21:55:21
*/
public interface ActivityService extends IService<Activity> {
    Object passActivityApproval(Activity activity);
    Object unPassActivityApproval(Activity activity);

}
