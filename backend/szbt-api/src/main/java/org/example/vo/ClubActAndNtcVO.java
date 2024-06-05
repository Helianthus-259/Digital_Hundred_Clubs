package org.example.vo;

import lombok.Data;
import org.example.dto.ActivityShowDTO;
import org.example.dto.ClubInfos;
import org.example.dto.NoticeDTO;
import org.example.enums.ResultCode;

import java.util.List;

@Data
public class ClubActAndNtcVO {
    private int code;
    private Integer clubId;
    private List<ActivityShowDTO> activities;
    private List<NoticeDTO> notices;

    public ClubActAndNtcVO(ResultCode resultCode, Integer clubId, List<ActivityShowDTO> activityShowDTOList,
                           List<NoticeDTO> noticeDTOList)
    {
        this.code=resultCode.getCode();
        this.clubId = clubId;
        this.activities = activityShowDTOList;
        this.notices = noticeDTOList;
    }
}
