package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpecialClubAnnualDTO {
    private String clubName;
    private Integer status;
    private Integer declarationId;
    @JsonProperty("reviewOpinion")
    private String departmentOpinion;
    private Integer declarationYear;
}
