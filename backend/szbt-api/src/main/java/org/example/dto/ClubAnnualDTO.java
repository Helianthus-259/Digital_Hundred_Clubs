package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ClubAnnualDTO {
    private String clubName;
    private Integer clubCategory;
    private Integer mainCampus;
    private Object clubDescription;
    private Integer totalMembers;
    private String meetingActivityListAttachment;
    private String externalSponsorshipAttachment;
    private String clubConstitutionAttachment;
    private String administrativeGuideTeacherName;
    private String businessGuideTeacherName;
    private Date establishmentDate;
    @JsonProperty("contactPerson")
    private String stName;
    @JsonProperty("contactPhone")
    private String contact;
    private Integer clubStatus;
    @JsonProperty("responsibleDepartment")
    private String departmentName;
    private String politicalStatus;
    private Integer isFinancialInformationPublic;
    @JsonProperty("publicityManagementInfo")
    private Object auditPublicityManagementInfo;
}
