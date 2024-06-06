package org.example.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ClubAwardDTO {
    private String awardName;

    private Date awardTime;

    private String issuingAuthority;

    private String imageUrl;

    private String clubEducationCaseAttachment;
}
