package com.szbt.clubserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Clubawards;

/**
* @author lingyi01
* @description 针对表【clubawards】的数据库操作Service
* @createDate 2024-06-04 17:28:16
*/
public interface ClubawardsService extends IService<Clubawards> {
    Object associationAwards(Integer clubId);
}
