package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.service.ClubService;
import org.example.dto.ClubDTO;
import org.example.dto.ExecutiveDTO;
import org.example.dto.PresidentDTO;
import org.example.dto.TotalMemberDTO;
import org.example.entity.*;
import com.szbt.clubserver.dao.mapper.ClubmemberMapper;
import com.szbt.clubserver.service.ClubmemberService;
import org.example.enums.Position;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.Result;
import org.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.entity.Clubapplicationrecord;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static org.example.constants.FileConstants.fileServerDownloadUrl;

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

        return clubmemberMapper.selectJoinList(ClubDTO.class, wrapper);
    }

    @Override
    public Object queryClubMemberByClubId(Integer clubId) {
        try{
            // 查询社长信息
            MPJLambdaWrapper<Clubmember> wrapper = new MPJLambdaWrapper<>();
            wrapper.select(Clubmember::getPosition)
                    .select(Student::getStName,
                            Student::getImageUrl,
                            Student::getContact)
                    .leftJoin(Student.class, Student::getStudentId, Clubmember::getStudentId)
                    .eq(Clubmember::getClubId, clubId)
                    .eq(Clubmember::getPosition, Position.PRESIDENT.getCode());
            PresidentDTO presidentDTO = clubmemberMapper.selectJoinOne(PresidentDTO.class, wrapper);
            //查询骨干信息
            MPJLambdaWrapper<Clubmember> wrapper2 = new MPJLambdaWrapper<>();
            wrapper2.select(Clubmember::getPosition)
                    .select(Student::getStName, Student::getImageUrl)
                    .leftJoin(Student.class, Student::getStudentId, Clubmember::getStudentId)
                    .eq(Clubmember::getClubId, clubId)
                    .and(w -> w.eq(Clubmember::getPosition, Position.VICE_PRESIDENT.getCode())
                            .or()
                            .eq(Clubmember::getPosition, Position.OFFICER.getCode()));
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
            presidentDTO.setImageUrl(fileServerDownloadUrl+imageUrl1);
            IntStream.range(0, executiveDTOS.size())
                    .forEach(i -> {
                        String imageUrl = executiveDTOS.get(i).getImageUrl();
                        executiveDTOS.get(i).setImageUrl(fileServerDownloadUrl+imageUrl);
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
}




