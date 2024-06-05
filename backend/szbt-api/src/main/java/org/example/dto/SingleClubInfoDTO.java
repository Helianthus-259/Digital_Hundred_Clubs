package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SingleClubInfoDTO {
    private String clubName;
    private Date establishmentDate;
    private Integer clubCategory;
    @JsonProperty("responsibleDepartment")
    private String departmentName;
    private String administrativeGuideTeacherName;
    private String businessGuideTeacherName;
    private Integer mainCampus;
    private Integer totalMembers;
    private Integer isFinancialInformationPublic;
    private String imageUrl;
}
