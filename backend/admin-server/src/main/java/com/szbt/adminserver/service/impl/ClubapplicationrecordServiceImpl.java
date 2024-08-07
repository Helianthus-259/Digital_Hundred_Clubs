package com.szbt.adminserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szbt.adminserver.dao.mapper.ClubapplicationrecordMapper;
import com.szbt.adminserver.service.ClubapplicationrecordService;
import org.example.entity.Clubapplicationrecord;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.ClubClientService;
import org.example.util.RedisKeyBuilder;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
* @author 小壳儿
* @description 针对表【clubapplicationrecord】的数据库操作Service实现
* @createDate 2024-05-31 19:19:57
*/
@Service
public class ClubapplicationrecordServiceImpl extends ServiceImpl<ClubapplicationrecordMapper, Clubapplicationrecord>
    implements ClubapplicationrecordService {
    @Autowired
    ClubapplicationrecordMapper clubapplicationrecordMapper;

    @Autowired
    ClubClientService clubClientService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Object passClubApproval(Clubapplicationrecord clubapplicationrecord) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        clubapplicationrecord.setUniversityStudentUnionReviewStatus(1);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
            Clubapplicationrecord clubapplicationrecord1 = clubapplicationrecordMapper.selectById(clubapplicationrecord.getRecordId());
            boolean passed = clubClientService.passClubApply(clubapplicationrecord1.getClubId());
            if(!passed) {
                // 如果未成功同步社团表，回滚事务并返回错误信息
                transactionManager.rollback(transactionStatus);
                //throw new RuntimeException("更改社团表失败");
                return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
            }
            transactionManager.commit(transactionStatus);
            return Result.success(new SingleCodeVO(ResultCode.PASS_CLUB_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
            return Result.send(StatusCode.PASS_CLUB_APPROVAL_ERROR,new SendMsg("通过社团申请失败"));
        }
    }

    @Override
    public Object unPassClubApproval(Clubapplicationrecord clubapplicationrecord) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        clubapplicationrecord.setUniversityStudentUnionReviewStatus(0);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
            Clubapplicationrecord clubapplicationrecord1 = clubapplicationrecordMapper.selectById(clubapplicationrecord.getRecordId());
            boolean passed = clubClientService.unPassClubApply(clubapplicationrecord1.getClubId());
            if(!passed) {
                // 如果未成功同步社团表，回滚事务并返回错误信息
                transactionManager.rollback(transactionStatus);
                return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
            }
            transactionManager.commit(transactionStatus);
            return Result.success(new SingleCodeVO(ResultCode.UN_PASS_CLUB_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
            return Result.send(StatusCode.UN_PASS_CLUB_APPROVAL_ERROR,new SendMsg("否决社团申请失败"));
        }
    }

    @Override
    public Object passCollegeClubApproval(Clubapplicationrecord clubapplicationrecord) {
        clubapplicationrecord.setCollegeReviewStatus(1);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.PASS_CLUB_COLLEGE_APPROVAL_ERROR,new SendMsg("学院通过社团申请失败"));
            String clubInfosListKey = RedisKeyBuilder.generateClubInfosListKey();
            clubClientService.deleteClubRedisData(clubInfosListKey);
            return Result.success(new SingleCodeVO(ResultCode.PASS_CLUB_COLLEGE_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.PASS_CLUB_COLLEGE_APPROVAL_ERROR,new SendMsg("学院通过社团申请失败"));
        }
    }

    @Override
    public Object unPassCollegeClubApproval(Clubapplicationrecord clubapplicationrecord) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        clubapplicationrecord.setCollegeReviewStatus(0);
        try{
            int updateById = clubapplicationrecordMapper.updateById(clubapplicationrecord);
            if(updateById<=0) return Result.send(StatusCode.UN_PASS_CLUB_COLLEGE_APPROVAL_ERROR,new SendMsg("学院否决社团申请失败"));
            Clubapplicationrecord clubapplicationrecord1 = clubapplicationrecordMapper.selectById(clubapplicationrecord.getRecordId());
            boolean passed = clubClientService.unPassClubApply(clubapplicationrecord1.getClubId());
            if(!passed) {
                // 如果未成功同步社团表，回滚事务并返回错误信息
                transactionManager.rollback(transactionStatus);
                return Result.send(StatusCode.UN_PASS_CLUB_COLLEGE_APPROVAL_ERROR,new SendMsg("学院否决社团申请失败"));
            }
            transactionManager.commit(transactionStatus);
            return Result.success(new SingleCodeVO(ResultCode.UN_PASS_CLUB_COLLEGE_APPROVAL));
        }catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
            return Result.send(StatusCode.UN_PASS_CLUB_COLLEGE_APPROVAL_ERROR,new SendMsg("学院否决社团申请失败"));
        }
    }
}




