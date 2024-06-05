package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ClubApplicationInfoDTO {
    private String clubName;
    private Date establishmentDate;
    @JsonProperty("responsibleDepartment")
    private String departmentName;
    private Integer mainCampus;
    private Object clubDescription;
    private Integer clubCategory;
    @JsonProperty("adminGuideTeacher")
    private String administrativeGuideTeacherName;
    @JsonProperty("businessGuideTeacher")
    private String businessGuideTeacherName;
    @JsonProperty("contactPerson")
    private String stName;
    @JsonProperty("contactPhone")
    private String contact;
    private Integer clubStatus;
    private String attachmentUrl;
    private String advisorResumeAttachmentUrl;
}
