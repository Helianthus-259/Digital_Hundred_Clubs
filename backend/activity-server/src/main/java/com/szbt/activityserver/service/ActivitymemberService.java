package com.szbt.activityserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.ActivityMemberDTO;
import org.example.entity.Activitymember;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【activitymember】的数据库操作Service
* @createDate 2024-05-30 21:23:54
*/
public interface ActivitymemberService extends IService<Activitymember> {
    List<ActivityMemberDTO> getActivityMemberBySid(Integer id);
}
