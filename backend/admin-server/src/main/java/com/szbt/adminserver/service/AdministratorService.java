package com.szbt.adminserver.service;

import org.example.entity.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
* @author 小壳儿
* @description 针对表【administrator】的数据库操作Service
* @createDate 2024-05-25 22:02:01
*/
public interface AdministratorService extends IService<Administrator> {
    Object updateAdminInfo(Administrator administrator);
}
