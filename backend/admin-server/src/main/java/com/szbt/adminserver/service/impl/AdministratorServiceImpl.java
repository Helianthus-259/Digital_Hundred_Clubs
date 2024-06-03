package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.adminserver.service.AdministratorService;
import org.example.entity.Administrator;
import com.szbt.adminserver.dao.mapper.AdministratorMapper;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 小壳儿
* @description 针对表【administrator】的数据库操作Service实现
* @createDate 2024-05-25 22:02:01
*/
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator>
    implements AdministratorService {
    @Autowired
    AdministratorMapper administratorMapper;
    @Override
    public Object updateAdminInfo(Administrator administrator) {
        int updateById = administratorMapper.updateById(administrator);
        if(updateById<=0) return Result.send(StatusCode.UPDATE_ADMIN_INFO_ERROR,new SendMsg("更新管理员信息失败"));
        return Result.success(new SingleCodeVO(ResultCode.UPDATE_ADMIN_INFO));
    }
}




