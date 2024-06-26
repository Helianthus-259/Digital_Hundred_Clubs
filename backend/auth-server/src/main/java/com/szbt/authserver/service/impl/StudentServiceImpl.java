package com.szbt.authserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.authserver.dao.mapper.StudentMapper;
import com.szbt.authserver.service.StudentService;
import com.szbt.authserver.util.SecurityUtil;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.JWTUtils;
import org.example.util.Result;
import org.example.vo.SLRSuccess;
import org.example.vo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(!ok) return Result.send(StatusCode.VERIFY_IMAGE_CODE_ERROR,new SendMsg("图形验证码错误"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Student student = studentMapper.selectOne(queryWrapper,true);
        System.out.println(student);
        if(student == null) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("用户名或密码错误"));
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(student);
        String token;
        System.out.println(password);
        if(SecurityUtil.decrypt(password,student.getPwd())) {
            token = jwtUtils.createJwt(jwtUser);
            return Result.success(new SLRSuccess(ResultCode.REGISTER_LOGIN, token, student.getStudentId()));
        }
        return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("用户名或密码错误"));
    }

    @Override
    public Object register(Student student, boolean ok) {
        String email = student.getEmail();
        String password = student.getPwd();
        if(!ok) return Result.send(StatusCode.VERIFY_MAIL_CODE_ERROR,new SendMsg("邮箱验证码错误"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if(studentMapper.exists(queryWrapper)) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("邮箱重复"));
        student.setPwd(SecurityUtil.encrypt(password));
        System.out.println(password);
        if(studentMapper.insert(student)==0) return Result.send(StatusCode.MYSQL_ERROR,new SendMsg("数据插入失败"));
        JWTUtils.JwtUser jwtUser = new JWTUtils.JwtUser(student);
        String token = jwtUtils.createJwt(jwtUser);
        return Result.success(new SLRSuccess(ResultCode.REGISTER_LOGIN,token,student.getStudentId()));
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




