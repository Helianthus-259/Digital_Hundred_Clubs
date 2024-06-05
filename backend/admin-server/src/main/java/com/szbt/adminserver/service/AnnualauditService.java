package com.szbt.adminserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Annualaudit;

/**
* @author 小壳儿
* @description 针对表【annualaudit】的数据库操作Service
* @createDate 2024-06-02 21:51:03
*/
public interface AnnualauditService extends IService<Annualaudit> {
    Object queryAllClubAnnuals();

    Object queryMyClubAnnualExamData(Integer clubId);
}
