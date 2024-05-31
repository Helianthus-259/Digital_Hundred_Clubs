package org.example.vo;

import lombok.Data;
import org.example.enums.ResultCode;

@Data
public class ALRSuccess {
    private Integer code;
    private String token;

    private Integer adminId;

    public ALRSuccess(ResultCode c, String t, Integer sid){
        this.code = c.getCode();
        this.token = t;
        this.adminId = sid;
    }
}
