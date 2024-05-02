package com.szbt.studentserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.studentserver.service.StudentService;
import com.szbt.studentserver.dao.mapper.StudentMapper;
import com.szbt.studentserver.util.Result;
import com.szbt.studentserver.util.SecurityUtil;
import lombok.val;
import org.example.vo.FailMsg;
import org.example.vo.LRSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.entity.Student;

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
    @Override
    public Object login(String email, String password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        Student student = studentMapper.selectOne(queryWrapper,true);
        if(SecurityUtil.decrypt(password,student.getPwd()))
            return Result.success(new LRSuccess(1,"admin",student.getStudentId()));
        return Result.send(1001,new FailMsg("注册失败"));
    }

    @Override
    public Object register(String email, String verifyCode, String password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        if(studentMapper.exists(queryWrapper)) return Result.send(1001,new FailMsg("注册失败"));
        val student = new Student();
        student.setEmail(email);
        student.setPwd(SecurityUtil.encrypt(password));
        if(studentMapper.insert(student)==0) return Result.send(1001,new FailMsg("注册失败"));
        return Result.success(new LRSuccess(1,"admin",student.getStudentId()));
    }
}




