package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityMemberDTO {
    private Integer index;
    private String activityName;
    private String personalEffect;
    private Date awardWiningTime;
}