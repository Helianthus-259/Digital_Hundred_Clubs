package com.szbt.authserver.controller;

import com.szbt.authserver.service.VerifyCodeService;
import com.szbt.authserver.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Student;
import org.example.service.FileClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class AuthController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private StudentClientService studentClientService;


    @PostMapping("/student/login")
    public Object login(String email, String password, String imageVerifyCode)
    {
        boolean ok = verifyCodeService.checkImageVerifyCode(imageVerifyCode);
        return studentService.login(email,password,ok);
    }

    @PostMapping("/student/register")
    public Object register(String email, String verifyCode, String password){
        boolean ok = verifyCodeService.checkMailVerifyCode(verifyCode,email);
        return studentService.register(email,password,ok);
    }

    @GetMapping("/verifyCode")
    public Object sendVerifyCode(String email){
        return verifyCodeService.sendMailVerifyCode(email);
    }

    @GetMapping("/getImageVerifyCode")
    public byte[] getImageVerifyCode() throws Exception {
        return verifyCodeService.sendImageVerifyCode();
    }

//    @PostMapping("/checkImageVerifyCode")
//    public Object checkImageVerifyCode(String code) {
//        return verifyCodeService.checkImageVerifyCode(code);
//    }
}