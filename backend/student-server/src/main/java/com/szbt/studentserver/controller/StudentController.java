package com.szbt.studentserver.controller;

import com.szbt.studentserver.service.BackboneevaluationService;
import com.szbt.studentserver.service.StudentService;
import com.szbt.studentserver.util.ImageResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.internal.lang.reflect.StringToType;
import org.example.constants.RequestKeyConstants;
import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;
import org.example.entity.Backboneevaluation;
import org.example.service.ActivityClientService;
import org.example.service.ClubClientService;
import org.example.service.FileClientService;
import org.example.service.StudentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private BackboneevaluationService backboneevaluationService;

    @Autowired
    private FileClientService fileClientService;

    @Autowired
    private ClubClientService clubClientService;

    @Autowired
    private ActivityClientService activityClientService;

//    @PostMapping("/uploadAvatar")
//    public Object uploadAvatar(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "studentId") Integer studentId){
//        String relativePath = fileClientService.uploadFile(file,"image/avatar/");
//        return studentService.savaAvatar(relativePath,studentId);
//    }

    @GetMapping("/userInfo")
    public Object getStudentInfoById(@RequestHeader(value = RequestKeyConstants.ID) Integer id){
        System.out.println(id);
        List<ClubDTO> clubDTOS = clubClientService.getClubInfoBySId(id);
        List<ActivityMemberDTO> activityMemberDTOS = activityClientService.getActivityMemberBySid(id);
        return studentService.getStudentInfoById(id,clubDTOS,activityMemberDTOS);
    }

    @PostMapping("/userInfoUpdate")
    public Object studentInfoUpdate(@RequestHeader(value = RequestKeyConstants.ID) Integer id,@ModelAttribute Student student){
        System.out.println(student);
        return studentService.studentInfoUpdate(student);
    }

    @PostMapping("/backBoneEvaluate")
    public Object addBackBoneEvaluate(@RequestHeader(value = RequestKeyConstants.ID) Integer id, @ModelAttribute Backboneevaluation backboneevaluation){
        System.out.println(backboneevaluation);
        return backboneevaluationService.addBackBoneEvaluate(backboneevaluation);
    }

    @GetMapping("/queryStudentInfo")
    public Student queryStudentInfo(Integer id){
        return studentService.getById(id);
    }

    @GetMapping("/getStudentByNumber")
    public List<Student>  getStudentByNumber(List<String> number){
        return studentService.getStudentByNumber(number);
    }
}