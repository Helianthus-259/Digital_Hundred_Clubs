package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.adminserver.service.AdministratorService;
import org.example.dto.AdminClubDTO;
import org.example.entity.Administrator;
import com.szbt.adminserver.dao.mapper.AdministratorMapper;
import org.example.entity.Club;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.AdminInfoVO;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
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
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Object updateAdminInfo(Administrator administrator) {
        int updateById = administratorMapper.updateById(administrator);
        if(updateById<=0) return Result.send(StatusCode.UPDATE_ADMIN_INFO_ERROR,new SendMsg("更新管理员信息失败"));
        return Result.success(new SingleCodeVO(ResultCode.UPDATE_ADMIN_INFO));
    }

    @Override
    public Object getAdminInfo(Integer adminId) {
        MPJLambdaWrapper<Administrator>  wrapper = new MPJLambdaWrapper<Administrator>()
                .select(Administrator::getDepartmentName)
                .eq(Administrator::getAdminId, adminId);
        try{
            String departmentName = administratorMapper.selectOne(wrapper).getDepartmentName();
            System.out.println(departmentName);
            return departmentName;
        }catch (Exception e){
            String exceptionAsString = e.toString();
            System.out.println(exceptionAsString);
            return "";
        }
    }

    @Override
    public Object getAdmin(Integer adminId, Club club) {
        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_id",adminId);
        try{
            Administrator administrator = administratorMapper.selectOne(wrapper, true);
            AdminInfoVO adminInfoVO = modelMapper.map(administrator, AdminInfoVO.class);
            AdminClubDTO clubDTO = modelMapper.map(club, AdminClubDTO.class);
            adminInfoVO.setClubs(clubDTO);
            return Result.success(new DataVO(ResultCode.GET_ADMIN_INFO,adminInfoVO));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ADMIN_INFO_ERROR,new SendMsg("获取管理员信息失败"));
    }
}




