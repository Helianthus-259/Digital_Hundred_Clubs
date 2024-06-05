package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class AdminClubDTO {
    private Integer clubId;
    private String clubName;
    @JsonProperty("establishedTime")
    private Date establishmentDate;
    @JsonProperty("affiliatedUnitId")
    private Integer responsibleDepartmentId;
    @JsonProperty("clubIntroduction")
    private Object clubDescription;
    @JsonProperty("clubSort")
    private Integer clubCategory;
    private Integer clubStatus;
    @JsonProperty("administrativeAdvisorName")
    private String administrativeGuideTeacherName;
    @JsonProperty("businessAdvisorName")
    private String businessGuideTeacherName;
    @JsonProperty("contactsId")
    private Integer contactPersonId;
    @JsonProperty("location")
    private Integer mainCampus;
    @JsonProperty("totalMembership")
    private Integer totalMembers;
    @JsonProperty("financePublicity")
    private Integer isFinancialInformationPublic;
}
