package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityShowDTO {
    private Integer activityId;
    private String activityName;
    private String activityIntroduction;
    private Date activityStartTime;
    private Date activityEndTime;
    private String activityLocation;
    private String activityEffect;
    private String imageUrl;
}
