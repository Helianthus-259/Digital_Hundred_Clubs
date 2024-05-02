package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "student-server")
public interface StudentClientService {
    @PostMapping("/student/login")
    public Object login(@RequestParam("email") String email,@RequestParam("password") String password);

    @RequestMapping("/student/register")
    public Object register(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode, @RequestParam("password") String password);
}
