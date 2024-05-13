package com.szbt.studentserver.controller;


import com.szbt.studentserver.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Student;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/login")
    public Object login(String email, String password)
    {
        return studentService.login(email,password);
    }

    @PostMapping("/register")
    public Object register(String email, String verifyCode, String password){
        return studentService.register(email,verifyCode,password);
    }

    @GetMapping("/verifyCode")
    public Object sendVerifyCode(String email){
        return studentService.sendVerifyCode(email);
    }

    @DeleteMapping("/delete/student")
    public Object deleteStudent(int id){
        return studentService.removeById(id);
    }
    @PostMapping("/studentInfo")
    public Object queryStudentById(int id){
        return studentService.getById(id);
    }

    @PostMapping("/update/student")
    public Object updateStudentById(Student st){
        return studentService.updateById(st);
    }
}
