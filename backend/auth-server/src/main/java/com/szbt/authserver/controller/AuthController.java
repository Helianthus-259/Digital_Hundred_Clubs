package com.szbt.authserver.controller;

import com.szbt.authserver.service.AdministratorService;
import com.szbt.authserver.service.VerifyCodeService;
import com.szbt.authserver.service.StudentService;
import jakarta.mail.MessagingException;
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
    private AdministratorService administratorService;

    @Autowired
    private StudentClientService studentClientService;


    @PostMapping("/login")
    public Object login(String email, String pwd, String imageVerifyCode)
    {
        boolean ok = verifyCodeService.checkImageVerifyCode(imageVerifyCode);
        System.out.println(email);
        return studentService.login(email,pwd,ok);
    }

    @PostMapping("/register")
    public Object register(String email, String verifyCode, String pwd){
        boolean ok = verifyCodeService.checkMailVerifyCode(verifyCode,email);
        return studentService.register(email,pwd,ok);
    }

    @PostMapping("/adminLogin")
    public Object adminLogin(String account, String password, String imageVerifyCode)
    {
        boolean ok = verifyCodeService.checkImageVerifyCode(imageVerifyCode);
        return administratorService.login(account,password,ok);
    }

    //用来临时添加管理员测试
    @PostMapping("/adminRegister")
    public Object adminRegister(String account,String password){
        return administratorService.register(account,password);
    }

    @GetMapping("/verifyCode")
    public Object sendVerifyCode(String email) throws MessagingException {
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