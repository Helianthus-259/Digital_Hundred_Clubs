package org.example.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ActivityEffectDTO {
    private String activityName;
    private Date activityEndTime;
    private String activityEffect;
}
