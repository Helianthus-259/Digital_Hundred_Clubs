package org.example.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDTO {
    private Integer studentId;
    private String stName;
    private String gender;
    private Date dateOfBirth;
    private String grade;
    private String studentNumber;
    private String college;
    private String politicalStatus;
    private String email;
    private String contact;
    private String hobby;
    private String specialty;
    private List<ClubDTO> clubs;
    private List<ActivityMemberDTO> achievements;
}
