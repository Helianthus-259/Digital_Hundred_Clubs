package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Clubmeeting;

/**
* @author lingyi01
* @description 针对表【clubmeeting】的数据库操作Service
* @createDate 2024-06-03 17:17:21
*/
public interface ClubmeetingService extends IService<Clubmeeting> {
    Object newMeeting(Clubmeeting clubmeeting);
}
