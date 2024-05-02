package org.example.vo;

import lombok.Data;

@Data
public class LRSuccess {
    private Integer code;
    private String token;

    private Integer uid;

    public LRSuccess(Integer c, String t,Integer sid){
        this.code = c;
        this.token = t;
        this.uid = sid;
    }

}
