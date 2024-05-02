package com.szbt.szbtclient.controller;

import jakarta.ws.rs.POST;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentClientService studentClientService;

    @PostMapping("/api/login")
    Object login(@RequestParam("email") String email, @RequestParam("password") String password){
        return studentClientService.login(email,password);
    }

    @PostMapping("/api/register")
    public Object register(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode, @RequestParam("password") String password){
        return studentClientService.register(email,verifyCode,password);
    }
}
