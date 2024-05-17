package com.szbt.studentserver.controller;

import com.szbt.studentserver.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.example.service.FileClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Student;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FileClientService fileClientService;

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

    @PostMapping("/uploadAvatar")
    public Object uploadAvatar(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "studentId") Integer studentId){
        String relativePath = fileClientService.uploadFile(file,"image/avatar/");
        return studentService.savaAvatar(relativePath,studentId);
    }
}