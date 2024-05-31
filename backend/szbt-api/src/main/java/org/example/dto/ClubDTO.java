package org.example.dto;

import lombok.Data;

@Data
public class ClubDTO {
    private Integer clubId;
    private String clubName;
    private String position; // 假设位置是社长
    private Integer clubStatus;
    private Integer collegeReviewStatus;
    private Integer universityStudentUnionReviewStatus;
    private String collegeReviewOpinion;
    private String universityStudentUnionReviewOpinion;
}

