package com.szbt.authserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.authserver.dao.mapper.AdministratorMapper;
import com.szbt.authserver.service.AdministratorService;
import com.szbt.authserver.util.SecurityUtil;
import org.example.entity.Administrator;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.JWTUtils;
import org.example.util.Result;
import org.example.vo.ALRSuccess;
import org.example.vo.SendMsg;
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
    private AdministratorMapper administratorMapper;

    @Autowired
    private JWTUtils jwtUtils;
    @Override
    public Object login(String account, String password, boolean ok) {
        if(!ok) return Result.send(StatusCode.VERIFY_MAIL_CODE_ERROR,new SendMsg("邮箱验证码错误"));
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        Administrator administrator = administratorMapper.selectOne(queryWrapper,true);
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(administrator);
        String token;
        if(SecurityUtil.decrypt(password,administrator.getPwd())) {
            token = jwtUtils.createJwt(jwtUser);
            return Result.success(new ALRSuccess(ResultCode.ADMIN_LOGIN, token, administrator.getAdminId()));
        }
        return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("登录失败"));
    }

    @Override
    public Object register(String account, String password) {
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        if(administratorMapper.exists(queryWrapper)) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
        Administrator administrator = new Administrator();
        administrator.setAccount(account);
        administrator.setPwd(SecurityUtil.encrypt(password));
        if(administratorMapper.insert(administrator)==0) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(administrator);
        String token = jwtUtils.createJwt(jwtUser);
        return Result.success(new ALRSuccess(ResultCode.REGISTER_LOGIN,token,administrator.getAdminId()));
    }
}




