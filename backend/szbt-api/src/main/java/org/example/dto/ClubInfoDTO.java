package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClubInfoDTO {
    private Integer clubId;
    private String clubName;
    private Object clubDescription;
    private Integer totalMembers;
    private Integer clubCategory;
    private Integer mainCampus;
    private String imageUrl;
    private Integer clubStatus;
    private Integer responsibleDepartmentId;
    private Date establishmentDate;
    private Integer collegeReviewStatus;
    private Integer universityStudentUnionReviewStatus;
}
