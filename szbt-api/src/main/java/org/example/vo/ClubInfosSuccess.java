package org.example.vo;

import lombok.Data;
import org.example.dto.ClubInfos;

import java.util.List;
@Data
public class ClubInfosSuccess {
    private Integer code;
    private List<ClubInfos> data;

    public ClubInfosSuccess(Integer c,List<ClubInfos> d)
    {
        this.code = c;
        this.data = d;
    }
}
