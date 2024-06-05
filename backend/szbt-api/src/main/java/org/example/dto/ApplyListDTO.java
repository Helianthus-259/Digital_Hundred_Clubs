package org.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApplyListDTO {
    private Integer studentId;
    private String stName;
    private String college;
    private String politicalStatus;
    private String email;
    private Integer status;
    private Date createTime;
    private String reason;
}
