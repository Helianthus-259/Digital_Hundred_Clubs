package com.szbt.studentserver.controller;

import com.szbt.studentserver.service.StudentService;
import com.szbt.studentserver.util.ImageResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.internal.lang.reflect.StringToType;
import org.example.constants.RequestKeyConstants;
import org.example.service.FileClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Student;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FileClientService fileClientService;

    @PostMapping("/uploadAvatar")
    public Object uploadAvatar(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "studentId") Integer studentId){
        String relativePath = fileClientService.uploadFile(file,"image/avatar/");
        return studentService.savaAvatar(relativePath,studentId);
    }

    @GetMapping("/studentInfo")
    public Object getStudentInfoByEmail(@RequestHeader(value = RequestKeyConstants.Account) String email){
        System.out.println(email);
        return studentService.getStudentInfoByEmail(email);
    }

}