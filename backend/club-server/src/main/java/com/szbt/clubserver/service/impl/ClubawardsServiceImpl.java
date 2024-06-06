package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubawardsMapper;
import com.szbt.clubserver.service.ClubawardsService;
import org.example.dto.ClubAwardDTO;
import org.example.entity.Club;
import org.example.entity.Clubawards;
import org.example.entity.Studentclubevaluation;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

/**
* @author lingyi01
* @description 针对表【clubawards】的数据库操作Service实现
* @createDate 2024-06-04 17:28:16
*/
@Service
public class ClubawardsServiceImpl extends ServiceImpl<ClubawardsMapper, Clubawards>
    implements ClubawardsService {

    @Autowired
    private ClubawardsMapper clubawardsMapper;
    @Override
    public Object associationAwards(Integer clubId) {
        MPJLambdaWrapper<Clubawards>  wrapper = new MPJLambdaWrapper<Clubawards>()
                .selectAll(Clubawards.class)
                .eq(Clubawards::getClubId,  clubId);
        try{
            List<Clubawards> clubAwards = clubawardsMapper.selectList(wrapper);
            return Result.success(new DataVO(ResultCode.GET_CLUB_AWARDS, clubAwards));
        }catch (Exception e){
            String exceptionAsString = e.toString();
            return Result.send(StatusCode.GET_CLUB_AWARDS_ERROR,new SendMsg(exceptionAsString));
        }
//        MPJLambdaWrapper<Clubawards>  wrapper = new MPJLambdaWrapper<Clubawards>()
//                .selectAll(Clubawards.class)
//                .select(Club::getImageUrl)
//                .select(Studentclubevaluation::getClubEducationCaseAttachment)
//                .leftJoin(Club.class,Club::getClubId,Clubawards::getClubId)
//                .leftJoin(Studentclubevaluation.class,Studentclubevaluation::getClubId,Clubawards::getClubId)
//                .eq(Clubawards::getClubId,  clubId);
//        try{
//            List<ClubAwardDTO> clubAwards = clubawardsMapper.selectJoinList(ClubAwardDTO.class,wrapper);
//            //处理文件请求
//            IntStream.range(0, clubAwards.size())
//                    .forEach(i -> {
//                        String imageUrl = clubAwards.get(i).getImageUrl();
//                        clubAwards.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
//                        String clubEducationCaseAttachment = clubAwards.get(i).getClubEducationCaseAttachment();
//                        clubAwards.get(i).setClubEducationCaseAttachment(FileRequestUrlBuilder.buildFileRequestUrl(clubEducationCaseAttachment));
//                    });
//            return Result.success(new DataVO(ResultCode.GET_CLUB_AWARDS, clubAwards));
//        }catch (Exception e){
//            String exceptionAsString = e.toString();
//            return Result.send(StatusCode.GET_CLUB_AWARDS_ERROR,new SendMsg(exceptionAsString));
//        }
    }
}




