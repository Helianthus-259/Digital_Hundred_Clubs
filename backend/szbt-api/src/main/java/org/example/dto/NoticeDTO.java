package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDTO {
    private Integer announcementId;
    private String title;
    private String content;
    private String imageUrl;
    private Date publishTime;
}
