package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AwardsDTO {
    private String awardName;
    private Date awardTime;
    private String issuingAuthority;
}
