package org.example.dto;

import lombok.Data;

@Data
public class SpecialClubAnnualDTO {
    private String clubName;
    private Integer status;
    private Integer declarationId;
    private String reviewOpinion;
    private Integer declarationYear;
}
