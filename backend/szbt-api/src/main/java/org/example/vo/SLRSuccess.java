package org.example.vo;

import lombok.Data;
import org.example.enums.ResultCode;

@Data
public class SLRSuccess {
    private Integer code;
    private String token;

    private Integer studentId;

    public SLRSuccess(ResultCode c, String t, Integer sid){
        this.code = c.getCode();
        this.token = t;
        this.studentId = sid;
    }
}
