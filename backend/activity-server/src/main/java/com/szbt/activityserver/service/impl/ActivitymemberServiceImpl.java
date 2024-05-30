package com.szbt.activityserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.jdbc.Null;
import org.example.dto.ActivityMemberDTO;
import org.example.entity.Activitymember;
import com.szbt.activityserver.dao.mapper.ActivitymemberMapper;
import com.szbt.activityserver.service.ActivitymemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【activitymember】的数据库操作Service实现
* @createDate 2024-05-30 21:23:54
*/
@Service
public class ActivitymemberServiceImpl extends ServiceImpl<ActivitymemberMapper, Activitymember>
    implements ActivitymemberService{

    @Autowired
    private ActivitymemberMapper activitymemberMapper;

    @Override
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        MPJLambdaWrapper<Activitymember> wrapper = new MPJLambdaWrapper<Activitymember>()
                .selectAll(Activitymember.class)
                .eq(Activitymember::getStudentId,id);
        List<ActivityMemberDTO> activityMemberDTOS = activitymemberMapper.selectJoinList(ActivityMemberDTO.class, wrapper);
        System.out.println(activityMemberDTOS);
        return activityMemberDTOS;
    }
}




