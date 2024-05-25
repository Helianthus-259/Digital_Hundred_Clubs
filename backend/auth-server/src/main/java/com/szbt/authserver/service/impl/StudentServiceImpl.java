package com.szbt.authserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.authserver.dao.mapper.StudentMapper;
import com.szbt.authserver.service.StudentService;
import com.szbt.authserver.util.EmailUtil;
import com.szbt.authserver.util.SecurityUtil;
import lombok.val;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.JWTUtils;
import org.example.util.Result;
import org.example.vo.LRSuccess;
import org.example.vo.SendMsg;
import org.example.vo.UploadSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author 小壳儿
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-04-23 14:12:37
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public Object login(String email, String password,boolean ok) {
        if(!ok) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("登录失败"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Student student = studentMapper.selectOne(queryWrapper,true);
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(student);
        String token;
        if(SecurityUtil.decrypt(password,student.getPwd())) {
            token = jwtUtils.createJwt(jwtUser);
            return Result.success(new LRSuccess(ResultCode.REGISTER_LOGIN, token, student.getStudentId()));
        }
        return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("登录失败"));
    }

    @Override
    public Object register(String email, String password, boolean ok) {
        if(!ok) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if(studentMapper.exists(queryWrapper)) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
        Student student = new Student();
        student.setEmail(email);
        student.setPwd(SecurityUtil.encrypt(password));
        if(studentMapper.insert(student)==0) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(student);
        String token = jwtUtils.createJwt(jwtUser);
        return Result.success(new LRSuccess(ResultCode.REGISTER_LOGIN,token,student.getStudentId()));
    }

//    @Override
//    public Object sendVerifyCode(String email) {
//        String verifyCode = emailUtil.generateCode (6);
//        if (emailUtil.sendMail(email,"数字百团验证服务","验证码:"+verifyCode))
//        {
//            // 保存验证码
//            emailCodeMap.put(email, verifyCode);
//            System.out.println(emailCodeMap.get(email));
//            return Result.success(new SendMsg("发送成功"));
//        }
//        return Result.send(StatusCode.SEND_VERIFY_CODE_ERROR,new SendMsg("发送失败"));
//    }


//    @Override
//    public Object checkImageVerifyCode(boolean ok)
//    {
//        if(ok) return Result.success(ok);
//        else return Result.send(StatusCode.VERIFY_IMAGE_CODE_ERROR,"图形验证码错误！");
//    }

}




