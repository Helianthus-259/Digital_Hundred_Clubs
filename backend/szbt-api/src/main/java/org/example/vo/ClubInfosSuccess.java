package org.example.vo;

import lombok.Data;
import org.example.dto.ClubInfos;
import org.example.util.ResultCode;

import java.util.List;
@Data
public class ClubInfosSuccess {
    private int code;
    private List<ClubInfos> data;

    public ClubInfosSuccess(ResultCode c, List<ClubInfos> d)
    {
        this.code = c.getCode();
        this.data = d;
    }
}
