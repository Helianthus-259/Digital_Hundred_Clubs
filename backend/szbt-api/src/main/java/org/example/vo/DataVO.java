package org.example.vo;

import lombok.Data;
import org.example.dto.ClubInfos;
import org.example.enums.ResultCode;

import java.util.List;

@Data
public class DataVO {
    private Integer code;
    private Object data;

    public DataVO(ResultCode code, Object data)
    {
        this.code = code.getCode();
        this.data = data;
    }
}
