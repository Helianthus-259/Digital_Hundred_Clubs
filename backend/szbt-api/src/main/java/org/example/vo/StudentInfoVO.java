package org.example.vo;

import lombok.Data;
import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;

import java.util.Date;
import java.util.List;

@Data
public class StudentInfoVO {
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
