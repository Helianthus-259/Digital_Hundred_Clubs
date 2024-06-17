package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDetailDTO {
    private Integer activityId;
    private Integer clubId;
    private String clubName;
    private String activityName;
    private Date activityPublishTime;
    private Date activityStartTime;
    private Date activityEndTime;
    private String activityLocation;
    private String activityIntroduction;
    private String applicationFormAttachment;
    private Integer activityStatus;
    private String activityEffect;
    private String imageUrl;
    private String contactPerson;
    private String contactPhone;
}
