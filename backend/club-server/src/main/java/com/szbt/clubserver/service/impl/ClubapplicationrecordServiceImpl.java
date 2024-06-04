package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.dto.ClubDTO;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import com.szbt.clubserver.dao.mapper.ClubapplicationrecordMapper;
import com.szbt.clubserver.service.ClubapplicationrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【clubapplicationrecord】的数据库操作Service实现
* @createDate 2024-05-31 19:19:57
*/
@Service
public class ClubapplicationrecordServiceImpl extends ServiceImpl<ClubapplicationrecordMapper, Clubapplicationrecord>
    implements ClubapplicationrecordService{
    @Autowired
    ClubapplicationrecordMapper clubapplicationrecordMapper;


    @Override
    public List<Clubapplicationrecord> queryClubApplicationRecordBySId(Integer id) {
        MPJLambdaWrapper<Clubapplicationrecord> wrapper = new MPJLambdaWrapper<Clubapplicationrecord>()
                .selectAll(Clubapplicationrecord.class)
                .eq(Clubapplicationrecord::getStudentId,id);
        List<Clubapplicationrecord> clubapplicationrecords = clubapplicationrecordMapper.selectJoinList(Clubapplicationrecord.class, wrapper);
        return clubapplicationrecords;
    }

    @Override
    public Object queryClubApplyList(Integer clubId) {
        return null;
    }
}




