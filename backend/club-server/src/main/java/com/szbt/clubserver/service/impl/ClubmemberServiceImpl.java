package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.dao.mapper.ClubmemberMapper;
import com.szbt.clubserver.service.ClubmemberService;
import org.example.dto.ClubDTO;
import org.example.dto.ExecutiveDTO;
import org.example.dto.PresidentDTO;
import org.example.dto.TotalMemberDTO;
import org.example.entity.Club;
import org.example.entity.Clubapplicationrecord;
import org.example.entity.Clubmember;
import org.example.entity.Student;
import org.example.enums.Position;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.service.StudentClientService;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.Result;
import org.example.vo.MemberVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
* @author 小壳儿
* @description 针对表【clubmember】的数据库操作Service实现
* @createDate 2024-05-31 19:40:13
*/
@Service
public class ClubmemberServiceImpl extends ServiceImpl<ClubmemberMapper, Clubmember>
    implements ClubmemberService{

    @Autowired
    ClubmemberMapper clubmemberMapper;

    @Autowired
    private ClubMapper clubMapper;
    @Autowired
    private StudentClientService studentClientService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public List<ClubDTO> queryAllClubMemberBySid(Integer id) {

        MPJLambdaWrapper<Clubmember> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Clubmember.class)
                .select(Club::getClubName, Club::getClubStatus)
                .select(Clubapplicationrecord::getCollegeReviewStatus, Clubapplicationrecord::getUniversityStudentUnionReviewStatus,
                        Clubapplicationrecord::getCollegeReviewOpinion, Clubapplicationrecord::getUniversityStudentUnionReviewOpinion)
                .leftJoin(Club.class, Club::getClubId, Clubmember::getClubId)
                .leftJoin(Clubapplicationrecord.class, Clubapplicationrecord::getClubId, Club::getClubId)
                .eq(Clubmember::getStudentId, id)
                .eq(Club::getClubStatus, 1);
        try{
            return clubmemberMapper.selectJoinList(ClubDTO.class, wrapper);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Object queryClubMemberByClubId(Integer clubId) {
        // 查询社长信息
        MPJLambdaWrapper<Clubmember> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Clubmember::getPosition)
                .select(Student::getStName,
                        Student::getImageUrl,
                        Student::getContact)
                .leftJoin(Student.class, Student::getStudentId, Clubmember::getStudentId)
                .eq(Clubmember::getClubId, clubId)
                .eq(Clubmember::getPosition, Position.PRESIDENT.getCode());
        //查询骨干信息
        MPJLambdaWrapper<Clubmember> wrapper2 = new MPJLambdaWrapper<>();
        wrapper2.select(Clubmember::getPosition)
                .select(Student::getStName, Student::getImageUrl)
                .leftJoin(Student.class, Student::getStudentId, Clubmember::getStudentId)
                .eq(Clubmember::getClubId, clubId)
                .and(w -> w.eq(Clubmember::getPosition, Position.VICE_PRESIDENT.getCode())
                        .or()
                        .eq(Clubmember::getPosition, Position.OFFICER.getCode()));
        try{
            PresidentDTO presidentDTO = clubmemberMapper.selectJoinOne(PresidentDTO.class, wrapper);
            List<ExecutiveDTO> executiveDTOS = clubmemberMapper.selectJoinList(ExecutiveDTO.class, wrapper2);
            //查询社团总人数
            MPJLambdaWrapper<Club> wrapper3 = new MPJLambdaWrapper<Club>()
                    .selectAll(Club.class)
                    .eq(Club::getClubId,clubId);
            Integer totalMembers = clubMapper.selectJoinOne(Club.class, wrapper3).getTotalMembers();
            TotalMemberDTO totalMemberDTO = new TotalMemberDTO();
            totalMemberDTO.setTotalMembers(totalMembers);
            //拼接文件请求
            String imageUrl1 = presidentDTO.getImageUrl();
            presidentDTO.setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl1));
            IntStream.range(0, executiveDTOS.size())
                    .forEach(i -> {
                        String imageUrl = executiveDTOS.get(i).getImageUrl();
                        executiveDTOS.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
                    });
            //创建返回对象
            MemberVO memberVO = new MemberVO(presidentDTO,executiveDTOS,totalMemberDTO);
            HashMap<String,Object> result = new HashMap<>();
            result.put("code", ResultCode.GET_ALL_CLUB_MEMBER.getCode());
            result.put("members",memberVO);
            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_MEMBER_ERROR,e.toString());
        }
    }

    @Override
    public Object updateClubMember(Integer clubId, String oldStudent, String newStudent) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        // 查询旧数据
        MPJLambdaWrapper<Clubmember>  wrapper1 = new MPJLambdaWrapper<Clubmember>()
                .selectAll(Clubmember.class)
                .eq(Clubmember::getClubId, clubId)
                .eq(Clubmember::getStudentId, oldStudent);
        // 查询新数据
        MPJLambdaWrapper<Clubmember>  wrapper2 = new MPJLambdaWrapper<Clubmember>()
                .selectAll(Clubmember.class)
                .eq(Clubmember::getClubId, clubId)
                .eq(Clubmember::getStudentId, newStudent);
        try{
            Clubmember oldClubmember  = clubmemberMapper.selectJoinOne(Clubmember.class, wrapper1);
            Clubmember newClubmember  = clubmemberMapper.selectJoinOne(Clubmember.class, wrapper2);
            // 交换职位
            Integer oldPosition = oldClubmember.getPosition();
            oldClubmember.setPosition(newClubmember.getPosition());
            newClubmember.setPosition(oldPosition);
            // 更新
            int updateById = clubmemberMapper.updateById(newClubmember);
            if (updateById<=0) return Result.send(StatusCode.UPDATE_CLUB_MEMBER_ERROR,new SendMsg("新干部数据更新失败"));
            updateById = clubmemberMapper.updateById(oldClubmember);
            if (updateById<=0) return Result.send(StatusCode.UPDATE_CLUB_MEMBER_ERROR,new SendMsg("旧干部数据更新失败"));
            return Result.success(new SingleCodeVO(ResultCode.UPDATE_CLUB_MEMBER));

        }catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        }
        return Result.send(StatusCode.UPDATE_CLUB_MEMBER_ERROR,new SendMsg("干部数据更新失败"));
    }

    @Override
    public Object addClubMember(Clubmember clubmember) {
        MPJLambdaWrapper<Clubmember>  wrapper = new MPJLambdaWrapper<Clubmember>()
                .selectAll(Clubmember.class)
                .eq(Clubmember::getClubId, clubmember.getClubId())
                .eq(Clubmember::getStudentId, clubmember.getStudentId());
        try{
            Clubmember newClubMember = clubmemberMapper.selectJoinOne(Clubmember.class, wrapper);
            newClubMember.setPosition(clubmember.getPosition());
            int updateById = clubmemberMapper.updateById(newClubMember);
            if (updateById>0) return Result.success(new SingleCodeVO(ResultCode.ADD_CLUB_MEMBER));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.send(StatusCode.ADD_CLUB_MEMBER_ERROR,new SendMsg("新增干部失败"));
    }

    @Override
    public Object deleteClubMember(Integer clubId, String studentNumber) {
        try{
            List<String> number = Collections.singletonList(studentNumber);
            System.out.println(number);
            List<Student> studentList = studentClientService.getStudentByNumber(number);
            Student student = studentList.get(0);
            System.out.println(student);
            MPJLambdaWrapper<Clubmember>  wrapper = new MPJLambdaWrapper<Clubmember>()
                    .selectAll(Clubmember.class)
                    .eq(Clubmember::getClubId, clubId)
                    .eq(Clubmember::getStudentId, student.getStudentId());
            Clubmember clubmember = clubmemberMapper.selectJoinOne(Clubmember.class, wrapper);
            clubmember.setPosition(Position.MEMBER.getCode());// 设置为普通成员
            int deleteById = clubmemberMapper.updateById(clubmember);
            if (deleteById<=0) return Result.send(StatusCode.DELETE_CLUB_MEMBER_ERROR,new SendMsg("删除干部失败"));
            return Result.success(new SingleCodeVO(ResultCode.DELETE_CLUB_MEMBER));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.DELETE_CLUB_MEMBER_ERROR,new SendMsg("删除干部失败"));
    }
}




