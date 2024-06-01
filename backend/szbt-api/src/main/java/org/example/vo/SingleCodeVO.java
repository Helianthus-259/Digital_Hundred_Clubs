package org.example.vo;

import lombok.Data;
import org.example.enums.ResultCode;

@Data
public class SingleCodeVO {
    private Integer code;
    public SingleCodeVO(ResultCode c){
        this.code = c.getCode();
    }
}
