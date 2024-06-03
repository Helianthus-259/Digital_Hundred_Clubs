package org.example.service;

import org.example.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "student-server")
public interface StudentClientService {
    @PostMapping("/student/login")
    public Object login(@RequestParam("email") String email,@RequestParam("password") String password);

    @PostMapping("/student/register")
    public Object register(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode, @RequestParam("password") String password);

//    @GetMapping("/student/verifyCode")
//    public Object sendVerifyCode(@RequestParam("email") String email);

    @PostMapping("/student/studentInfo")
    public Student getStudentInfoByEmail(@RequestParam("email") String email);

    @GetMapping("/student/queryStudentInfo")
    public Student queryStudentInfo(@RequestParam("id") Integer id);

    @GetMapping("/student/getStudentByNumber")
    public List<Student> getStudentByNumber(List<String> number);
}
