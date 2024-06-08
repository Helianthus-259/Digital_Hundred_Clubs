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
import org.example.service.StudentClientService;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
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

    @Autowired
    private StudentClientService studentClientService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public List<ActivityMemberDTO> getActivityMemberBySid(Integer id) {
        System.out.println(id);
        MPJLambdaWrapper<Activitymember> wrapper = new MPJLambdaWrapper<Activitymember>()
                .selectAll(Activitymember.class)
                .eq(Activitymember::getStudentId,id);
        //List<ActivityMemberDTO> activityMemberDTOS = activitymemberMapper.selectJoinList(ActivityMemberDTO.class, wrapper);
        try {
            List<Activitymember> activityMembers = activitymemberMapper.selectJoinList(Activitymember.class, wrapper);
            // 使用 ModelMapper 映射
            List<ActivityMemberDTO> activityMemberDTOS = modelMapper.map(activityMembers, new TypeToken<List<ActivityMemberDTO>>() {}.getType());
            for (int i = 0; i < activityMemberDTOS.size(); i++) {
                activityMemberDTOS.get(i).setIndex(activityMembers.get(i).getActivityMemberId());
            }
            System.out.println(activityMemberDTOS);
            return activityMemberDTOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object joinActivity(String studentNumber,Activity activity) {
        try{
            List<String> studentNumberList = new ArrayList<>();
            studentNumberList.add(studentNumber);
            List<Student> studentList = studentClientService.getStudentByNumber(studentNumberList);
            Integer studentId = studentList.get(0).getStudentId();
            String activityName = activity.getActivityName();
            int  activityId = activity.getActivityId();
            Activitymember activitymember  = new Activitymember();
            activitymember.setActivityId(activityId);
            activitymember.setActivityName(activityName);
            activitymember.setStudentId(studentId);
            int inserted = activitymemberMapper.insert(activitymember);
            if(inserted<=0) return Result.send(StatusCode.JOIN_ACTIVITY_ERROR,"加入活动失败");
            return Result.success(new SingleCodeVO(ResultCode.JOIN_ACTIVITY));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.JOIN_ACTIVITY_ERROR,"加入活动失败");
    }

    @Override
    public Object personalPerformance(Activity activity, List<ActivityEffectGroup> activityEffectGroup, List<String> studentNumberList) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        String activityName = activity.getActivityName();
        int  activityId = activity.getActivityId();
        try{
            IntStream.range(0, activityEffectGroup.size()).forEach(i->{
                // Activitymember activitymember  = new Activitymember();
                // activitymember.setActivityId(activityId);
                // activitymember.setActivityName(activityName);
                // activitymember.setStudentId(studentList.get(i).getStudentId());
                // activitymember.setPersonalEffect(activityEffectGroup.get(i).getPersonalEffect());
                // activitymemberMapper.insert(activitymember);
                List<Student> studentList = studentClientService.getStudentByNumber(studentNumberList);
                System.out.println(studentList);
                QueryWrapper<Activitymember> wrapper = new QueryWrapper<>();
                wrapper.eq("student_id",studentList.get(i).getStudentId());
                wrapper.eq("activity_id",activityId);
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
            });
            return Result.success(new SingleCodeVO(ResultCode.ADD_PERSONAL_PERFORMANCE));
        }catch (Exception e) {
            e.printStackTrace();
            //回滚
            transactionManager.rollback(transactionStatus);
        }
        return Result.send(StatusCode.ADD_PERSONAL_PERFORMANCE_ERROR,new SendMsg("提交个人绩效失败"));
    }
}




