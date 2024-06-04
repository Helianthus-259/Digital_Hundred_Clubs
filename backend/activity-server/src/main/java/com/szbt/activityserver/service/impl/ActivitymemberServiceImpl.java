package com.szbt.activityserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.activityserver.dao.mapper.ActivitymemberMapper;
import com.szbt.activityserver.service.ActivitymemberService;
import org.example.dto.ActivityEffectGroup;
import org.example.dto.ActivityMemberDTO;
import org.example.entity.Activity;
import org.example.entity.Activitymember;
import org.example.entity.Student;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        System.out.println(id);
        MPJLambdaWrapper<Activitymember> wrapper = new MPJLambdaWrapper<Activitymember>()
                .selectAll(Activitymember.class)
                .eq(Activitymember::getStudentId,id);
        //List<ActivityMemberDTO> activityMemberDTOS = activitymemberMapper.selectJoinList(ActivityMemberDTO.class, wrapper);
        List<Activitymember> activityMembers = activitymemberMapper.selectJoinList(Activitymember.class, wrapper);
        // 使用 ModelMapper 映射
        List<ActivityMemberDTO> activityMemberDTOS = modelMapper.map(activityMembers, new TypeToken<List<ActivityMemberDTO>>() {}.getType());
        for (int i = 0; i < activityMemberDTOS.size(); i++) {
            activityMemberDTOS.get(i).setIndex(activityMembers.get(i).getActivityMemberId());
        }
        System.out.println(activityMemberDTOS);
        return activityMemberDTOS;
    }

    @Override
    public Object joinActivity(Student student,Activity activity) {
        String activityName = activity.getActivityName();
        int  activityId = activity.getActivityId();
        Activitymember activitymember  = new Activitymember();
        activitymember.setActivityId(activityId);
        activitymember.setActivityName(activityName);
        activitymember.setStudentId(student.getStudentId());
        try{
            int inserted = activitymemberMapper.insert(activitymember);
            if(inserted<=0) return Result.send(StatusCode.JOIN_ACTIVITY_ERROR,"加入活动失败");
            return Result.success(new SingleCodeVO(ResultCode.JOIN_ACTIVITY));
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.send(StatusCode.JOIN_ACTIVITY_ERROR,"加入活动失败");
    }

    @Override
    public Object personalPerformance(Activity activity, List<ActivityEffectGroup> activityEffectGroup, List<Student> studentList) {
        String activityName = activity.getActivityName();
        int  activityId = activity.getActivityId();
        IntStream.range(0, activityEffectGroup.size()).forEach(i->{
            // Activitymember activitymember  = new Activitymember();
            // activitymember.setActivityId(activityId);
            // activitymember.setActivityName(activityName);
            // activitymember.setStudentId(studentList.get(i).getStudentId());
            // activitymember.setPersonalEffect(activityEffectGroup.get(i).getPersonalEffect());
            try {
                // activitymemberMapper.insert(activitymember);
                QueryWrapper<Activitymember> wrapper = new QueryWrapper<>();
                wrapper.eq("studentId",studentList.get(i).getStudentId());
                wrapper.eq("activityId",activityId);
                Activitymember activitymember = activitymemberMapper.selectOne(wrapper, true);
                if (activitymember == null)
                {
                    activitymember.setActivityId(activityId);
                    activitymember.setActivityName(activityName);
                    activitymember.setStudentId(studentList.get(i).getStudentId());
                    activitymember.setPersonalEffect(activityEffectGroup.get(i).getPersonalEffect());
                    activitymember.setAwardWiningTime(new Date());
                    activitymemberMapper.insert(activitymember);
                }
                else
                {
                    activitymember.setPersonalEffect(activityEffectGroup.get(i).getPersonalEffect());
                    activitymember.setAwardWiningTime(new Date());
                    activitymemberMapper.updateById(activitymember);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return Result.success(new SingleCodeVO(ResultCode.ADD_PERSONAL_PERFORMANCE));
    }
}




