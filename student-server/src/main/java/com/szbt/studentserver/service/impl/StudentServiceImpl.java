package com.szbt.studentserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.studentserver.service.StudentService;
import com.szbt.studentserver.dao.mapper.StudentMapper;
import com.szbt.studentserver.util.EmailUtil;
import com.szbt.studentserver.util.Result;
import com.szbt.studentserver.util.SecurityUtil;
import lombok.val;
import org.example.vo.SendMsg;
import org.example.vo.LRSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.example.entity.Student;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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
    private EmailUtil emailUtil;

    // 先用map存储已发送的验证码，后面可以改用redis
    private final Map<String, String> emailCodeMap = new ConcurrentHashMap<>(16);

    @Override
    public Object login(String email, String password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Student student = studentMapper.selectOne(queryWrapper,true);
        if(SecurityUtil.decrypt(password,student.getPwd()))
            return Result.success(new LRSuccess(1,"admin",student.getStudentId()));
        return Result.send(1001,new SendMsg("登录失败"));
    }

    @Override
    public Object register(String email, String verifyCode, String password) {
        if(!verifyCode.equals(emailCodeMap.get(email))) return Result.send(1001,new SendMsg("注册失败"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if(studentMapper.exists(queryWrapper)) return Result.send(1001,new SendMsg("注册失败"));
        val student = new Student();
        student.setEmail(email);
        student.setPwd(SecurityUtil.encrypt(password));
        if(studentMapper.insert(student)==0) return Result.send(1001,new SendMsg("注册失败"));
        return Result.success(new LRSuccess(1,"admin",student.getStudentId()));
    }

    @Override
    public Object sendVerifyCode(String email) {
        String verifyCode = emailUtil.generateCode (6);
        if (emailUtil.sendMail(email,"数字百团验证服务","验证码:"+verifyCode))
        {

            // 保存验证码
            emailCodeMap.put(email, verifyCode);
            System.out.println(emailCodeMap.get(email));
            return Result.send(200,new SendMsg("发送成功"));
        }
        return Result.send(10086,new SendMsg("发送失败"));
    }
}




