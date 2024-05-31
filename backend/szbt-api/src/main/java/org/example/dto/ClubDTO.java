package org.example.dto;

import lombok.Data;

@Data
public class ClubDTO {
    private Integer clubId;
    private String clubName;
    private String position;
    private Integer clubStatus;
    private Integer collegeReviewStatus;
    private Integer universityStudentUnionReviewStatus;
    private String collegeReviewOpinion;
    private String universityStudentUnionReviewOpinion;
}

