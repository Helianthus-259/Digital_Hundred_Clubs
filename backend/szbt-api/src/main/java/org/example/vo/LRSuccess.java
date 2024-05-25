package org.example.vo;

import lombok.Data;
import org.example.enums.ResultCode;

@Data
public class LRSuccess {
    private int code;
    private String token;

    private Integer uid;

    public LRSuccess(ResultCode c, String t, Integer sid){
        this.code = c.getCode();
        this.token = t;
        this.uid = sid;
    }
}
