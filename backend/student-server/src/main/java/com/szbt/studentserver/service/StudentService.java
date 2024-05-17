package com.szbt.studentserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Student;

/**
* @author 小壳儿
* @description 针对表【student】的数据库操作Service
* @createDate 2024-04-23 14:12:37
*/
public interface StudentService extends IService<Student> {
    Object login(String email, String password);
    Object register(String email, String verifyCode, String password);

    Object sendVerifyCode(String email);

    Object savaAvatar(String relativePath, Integer studentId);
}
