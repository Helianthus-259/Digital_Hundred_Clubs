package com.szbt.adminserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Administrator;
import org.example.entity.Club;

/**
* @author 小壳儿
* @description 针对表【administrator】的数据库操作Service
* @createDate 2024-05-25 22:02:01
*/
public interface AdministratorService extends IService<Administrator> {
    Object updateAdminInfo(Administrator administrator);
    Object getAdminInfo(Integer adminId);

    Object getAdmin(Integer adminId, Club club);
}
