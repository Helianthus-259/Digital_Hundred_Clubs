package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingDTO {
    private Integer meetingId;
    private Integer clubId;
    private Date meetingTime;
    private String location;
    private Integer category;
    private String advisorName;
}
