package org.example.dto;

import lombok.Data;

@Data
public class SingleClubEvaluationDTO {
    private String clubName;
    private Integer totalMembers;
    private Integer backboneNumber;
    private Integer communistRelatedBackBoneNumber;
    private String administrativeGuideTeacherName;
    private String businessGuideTeacherName;
    private Integer isFinancialInformationPublic;
}
