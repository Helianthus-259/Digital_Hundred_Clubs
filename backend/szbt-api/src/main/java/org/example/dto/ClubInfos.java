package org.example.dto;

import lombok.Data;
import org.example.entity.Club;

@Data
public class ClubInfos {
    private String src;
    private String name;
    private Object introduction;
    private Integer clubID;
    private String type;

    // Helper method to map Club entity to ClubInfo
    public static ClubInfos mapClubToClubInfo(Club club) {
        ClubInfos clubInfo = new ClubInfos();
        clubInfo.setName(club.getClubName());
        clubInfo.setIntroduction(club.getClubDescription());
        clubInfo.setClubID(club.getClubId());
        clubInfo.setType("体育类");//那个配置表还没弄
        clubInfo.setSrc(club.getImageUrl());
        return clubInfo;
    }


}



