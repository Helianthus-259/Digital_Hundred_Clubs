package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.adminserver.dao.mapper.AdministratorMapper;
import com.szbt.adminserver.service.AdministratorService;
import org.example.dto.AdminClubDTO;
import org.example.dto.DepartmentDTO;
import org.example.entity.Administrator;
import org.example.entity.Club;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.AdminInfoVO;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try{
            int updateById = administratorMapper.updateById(administrator);
            if(updateById > 0) return Result.success(new SingleCodeVO(ResultCode.UPDATE_ADMIN_INFO));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.UPDATE_ADMIN_INFO_ERROR,new SendMsg("更新管理员信息失败"));
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
    public Object getAdmin(Integer adminId, List<Club> clubList) {
        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_id",adminId);
        try{
            Administrator administrator = administratorMapper.selectOne(wrapper, true);
            AdminInfoVO adminInfoVO = modelMapper.map(administrator, AdminInfoVO.class);
            List<AdminClubDTO> clubDTOList = modelMapper.map(clubList, new TypeToken<List<AdminClubDTO>>() {}.getType());
            adminInfoVO.setClubs(clubDTOList);
            return Result.success(new DataVO(ResultCode.GET_ADMIN_INFO,adminInfoVO));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ADMIN_INFO_ERROR,new SendMsg("获取管理员信息失败"));
    }

    @Override
    public Object getDepartmentList() {
        MPJLambdaWrapper<Administrator>  wrapper = new MPJLambdaWrapper<Administrator>()
                .selectAll(Administrator.class);
        try{
            List<DepartmentDTO> departmentDTOS = administratorMapper.selectJoinList(DepartmentDTO.class, wrapper);
            // 构造结果对象
            Map<String, Object> result = new HashMap<>();
            result.put("code", ResultCode.GET_DEPARTMENT_LIST.getCode());
            result.put("departmentList", departmentDTOS);
            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_DEPARTMENT_LIST_ERROR,new SendMsg("获取管理员列表失败"));
    }
}




