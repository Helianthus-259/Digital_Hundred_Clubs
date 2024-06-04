package org.example.vo;

import lombok.Data;
import org.example.enums.ResultCode;

import java.util.List;

@Data
public class ClubDescriptionVO {
    private int code;
    private Object clubDescription;
    public ClubDescriptionVO(ResultCode resultCode, Object clubDescription)
    {
        this.code = resultCode.getCode();
        this.clubDescription =clubDescription;
    }
}
