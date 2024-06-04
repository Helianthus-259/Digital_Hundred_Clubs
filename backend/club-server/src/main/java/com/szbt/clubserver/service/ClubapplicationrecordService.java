package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Clubapplicationrecord;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubapplicationrecord】的数据库操作Service
* @createDate 2024-05-31 19:19:57
*/
public interface ClubapplicationrecordService extends IService<Clubapplicationrecord> {
    List<Clubapplicationrecord> queryClubApplicationRecordBySId(Integer id);

    Object queryClubApplyList(Integer clubId);
}
