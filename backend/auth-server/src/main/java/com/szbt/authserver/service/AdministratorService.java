package com.szbt.authserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Administrator;

/**
* @author 小壳儿
* @description 针对表【administrator】的数据库操作Service
* @createDate 2024-05-25 22:02:01
*/
public interface AdministratorService extends IService<Administrator> {
    Object login(String account, String password, boolean ok);
    Object register(String account, String password);
}
