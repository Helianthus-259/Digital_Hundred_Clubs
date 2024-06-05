package org.example.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.dto.AdminClubDTO;

import java.util.List;

@Data
public class AdminInfoVO {
    private Integer adminId;
    private String account;
    @JsonProperty("sort")
    private Integer authority;
//    @JsonProperty("password")
//    private String pwd;
    private String contact;
    @JsonProperty("affiliatedUnit")
    private String departmentName;
    AdminClubDTO clubs;
}
