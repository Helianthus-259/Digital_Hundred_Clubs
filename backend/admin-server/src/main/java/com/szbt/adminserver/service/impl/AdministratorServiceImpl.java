package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.adminserver.service.AdministratorService;
import org.example.entity.Administrator;
import com.szbt.adminserver.dao.mapper.AdministratorMapper;
import org.springframework.stereotype.Service;

/**
* @author 小壳儿
* @description 针对表【administrator】的数据库操作Service实现
* @createDate 2024-05-25 22:02:01
*/
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator>
    implements AdministratorService {

}




