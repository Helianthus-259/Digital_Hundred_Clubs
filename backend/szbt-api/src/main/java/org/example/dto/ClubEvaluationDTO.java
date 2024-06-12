package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClubEvaluationDTO {
    private Integer recordId;
    private Integer declarationYear;
    private Integer clubId;
    private String clubName;
    private String handoverMethod;
    private Integer handoverParticipantsCount;
    private Integer advisorParticipation;
    private Object meetings;
    private Object associationAwards;
    private Object publicityManagementEffectiveness;
    private Object hostedSchoolLevelActivities;
    private List<ActivityEffectDTO> activities;
    private String clubWorkIntroduction;
    private Integer status;
}
