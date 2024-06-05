package com.szbt.authserver.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szbt.authserver.service.EnumService;
import org.example.enums.*;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnumServiceImpl implements EnumService {
    @Override
    public Object getEnumList() {
        List<EnumWrapper.EnumData> positionData = Arrays.stream(Position.values())
                .map(position -> new EnumWrapper.EnumData(position.getName(), position.getCode()))
                .collect(Collectors.toList());

        List<EnumWrapper.EnumData> mainCampusData = Arrays.stream(MainCampus.values())
                .map(mainCampus -> new EnumWrapper.EnumData(mainCampus.getName(), mainCampus.getCode()))
                .collect(Collectors.toList());

        List<EnumWrapper.EnumData> clubCategoryData = Arrays.stream(ClubCategory.values())
                .map(clubCategory -> new EnumWrapper.EnumData(clubCategory.getName(), clubCategory.getCode()))
                .collect(Collectors.toList());

        List<EnumWrapper.EnumData> meetingData = Arrays.stream(Meeting.values())
                .map(meeting -> new EnumWrapper.EnumData(meeting.getName(), meeting.getCode()))
                .collect(Collectors.toList());

        List<EnumWrapper.EnumData> genderData = Arrays.stream(Gender.values())
                .map(gender -> new EnumWrapper.EnumData(gender.getName(), gender.getCode()))
                .collect(Collectors.toList());

        List<EnumWrapper.EnumData> statusCodeData = Arrays.stream(StatusCode.values())
                .map(statusCode -> new EnumWrapper.EnumData(statusCode.getName(), statusCode.getCode()))
                .collect(Collectors.toList());


        EnumWrapper enumWrapper = new EnumWrapper();
        enumWrapper.setPositions(positionData);
        enumWrapper.setMainCampuses(mainCampusData);
        enumWrapper.setClubCategories(clubCategoryData);
        enumWrapper.setMeetings(meetingData);
        enumWrapper.setGenders(genderData);
        enumWrapper.setStatusCodes(statusCodeData);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(enumWrapper);
            //System.out.println(json);
            return Result.success(new DataVO(ResultCode.GET_ENUM_LIST,enumWrapper));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ENUM_LIST_ERROR,new SendMsg("获取枚举类别失败"));
    }
}
