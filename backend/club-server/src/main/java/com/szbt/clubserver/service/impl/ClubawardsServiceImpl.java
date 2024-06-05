package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubawardsMapper;
import com.szbt.clubserver.service.ClubawardsService;
import org.example.entity.Clubawards;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    }
}




