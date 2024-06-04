package org.example.dto;

import lombok.Data;

@Data
public class ClubActivityListDTO {
    private Integer activityId;
    private String activityName;
    private String imageUrl;
    private Integer activityStatus;
    private String activityLocation;
    private Integer status;

}
