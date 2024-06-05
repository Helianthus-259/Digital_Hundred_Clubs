package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.ActivityEffectDTO;
import org.example.dto.ClubEvaluationDTO;
import org.example.entity.*;
import com.szbt.adminserver.dao.mapper.StudentclubevaluationMapper;
import com.szbt.adminserver.service.StudentclubevaluationService;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.ActivityClientService;
import org.example.service.ClubClientService;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* @author 小壳儿
* @description 针对表【studentclubevaluation】的数据库操作Service实现
* @createDate 2024-06-02 23:31:05
*/
@Service
public class StudentclubevaluationServiceImpl extends ServiceImpl<StudentclubevaluationMapper, Studentclubevaluation>
    implements StudentclubevaluationService{

    @Autowired
    StudentclubevaluationMapper studentclubevaluationMapper;

    @Autowired
    private ActivityClientService activityClientService;

    @Autowired
    private ClubClientService clubClientService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Object queryAllClubEvaluations() {
        MPJLambdaWrapper<Studentclubevaluation> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Studentclubevaluation.class);
        try{
            List<ClubEvaluationDTO> clubEvaluationDTOS = studentclubevaluationMapper.selectJoinList(ClubEvaluationDTO.class, wrapper);
            List<Integer> clubIdList = clubEvaluationDTOS.stream()
                    .map(ClubEvaluationDTO::getClubId)
                    .collect(Collectors.toList());
            List<Club> clubList = clubClientService.getClubList(clubIdList);
            List<List<Activity>> activityLists = activityClientService.queryActivityInfoByClubIdList(clubIdList);
            IntStream.range(0, clubEvaluationDTOS.size())
                    .forEach(i -> {
                        clubEvaluationDTOS.get(i).setClubName(clubList.get(i).getClubName());
                        List<ActivityEffectDTO> activityEffectDTO = modelMapper.map(activityLists.get(i), new TypeToken<List<ActivityEffectDTO>>() {}.getType());
                        clubEvaluationDTOS.get(i).setActivities(activityEffectDTO);
                    });
            return Result.success(new DataVO(ResultCode.GET_ALL_CLUB_EVALUATION,clubEvaluationDTOS));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_All_Club_EVALUATION_ERROR,new SendMsg("获取社团评优信息失败"));
        }
    }
}




