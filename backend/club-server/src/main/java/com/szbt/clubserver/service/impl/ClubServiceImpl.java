package com.szbt.clubserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.clubserver.dao.mapper.ClubMapper;
import com.szbt.clubserver.service.ClubService;
import com.szbt.clubserver.service.ClubapplicationrecordService;
import com.szbt.clubserver.service.ClubmemberService;
import org.example.dto.*;
import org.example.entity.*;
import org.example.enums.ResultCode;
import org.example.enums.StatusCode;
import org.example.util.FileRequestUrlBuilder;
import org.example.util.MyJsonParser;
import org.example.util.Result;
import org.example.vo.ClubDescriptionVO;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* @author 小壳儿
* @description 针对表【club】的数据库操作Service实现
* @createDate 2024-05-03 03:11:18
*/
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club>
    implements ClubService {
    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private ClubapplicationrecordService clubapplicationrecordService;

    @Autowired
    private ClubmemberService clubmemberService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Object queryAllClubs() {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Club.class);
        try{
            List<ClubInfoDTO> clubInfoDTOS = clubMapper.selectJoinList(ClubInfoDTO.class, wrapper);
            //处理文件请求 && 社团描述转json
            IntStream.range(0, clubInfoDTOS.size())
                    .forEach(i -> {
                        Object clubDescription = clubInfoDTOS.get(i).getClubDescription();
                        clubDescription = MyJsonParser.parserJsonText(clubDescription);
                        String imageUrl = clubInfoDTOS.get(i).getImageUrl();
                        clubInfoDTOS.get(i).setImageUrl(FileRequestUrlBuilder.buildFileRequestUrl(imageUrl));
                        clubInfoDTOS.get(i).setClubDescription(clubDescription);
                    });
            return Result.success(new DataVO(ResultCode.GET_CLUB_INFO,clubInfoDTOS));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_ALL_CLUB_INFO_ERROR,new SendMsg("获取所有社团信息失败"));
    }


    @Override
    public List<ClubDTO> getClubInfoBySId(Integer id) {

        List<ClubDTO> clubDTOS1 = clubmemberService.queryAllClubMemberBySid(id);
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Club::getClubId, Club::getClubName, Club::getClubStatus)
                .select(Clubapplicationrecord::getCollegeReviewStatus,
                        Clubapplicationrecord::getUniversityStudentUnionReviewStatus,
                        Clubapplicationrecord::getCollegeReviewOpinion,
                        Clubapplicationrecord::getUniversityStudentUnionReviewOpinion)
                .leftJoin(Clubapplicationrecord.class, Clubapplicationrecord::getClubId, Club::getClubId)
                .eq(Club::getContactPersonId, id)
                .eq(Club::getClubStatus, 0);
        try{
            List<ClubDTO> clubDTOS = clubMapper.selectJoinList(ClubDTO.class, wrapper);
            clubDTOS.addAll(clubDTOS1);
            System.out.println(clubDTOS);
            return clubDTOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Club getClubInfoById(Integer id) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .eq(Club::getClubId,id);
        try{
            Club clubInfo = clubMapper.selectJoinOne(Club.class, wrapper);
            System.out.println(clubInfo);
            return clubInfo;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Club> getClubList(List<Integer> idList) {
//        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
//                .selectAll(Club.class)
//                .in(Club::getClubId, idList);
//        List<Club> clubList = clubMapper.selectJoinList(Club.class, wrapper);
//        System.out.println(clubList);
//        return clubList;
        return clubMapper.selectBatchIds(idList);
    }

    @Override
    public Object queryClubIntroductionByClubId(Integer clubId) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .eq(Club::getClubId,clubId);
        try {
            Object clubDescription = clubMapper.selectOne(wrapper, true).getClubDescription();
            System.out.println(clubDescription.getClass());
            System.out.println(clubDescription);
            clubDescription = MyJsonParser.parserJsonText(clubDescription);
            System.out.println(clubDescription);
            return Result.success(new ClubDescriptionVO(ResultCode.GET_CLUB_INTRODUCTION,clubDescription));
        } catch (Exception e) {
            String exceptionAsString = e.toString();
            return Result.send(StatusCode.GET_CLUB_INTRODUCTION_ERROR,new SendMsg(exceptionAsString));
        }
    }

    @Override
    public Object updateClubDescription(Club club) {
        int updateById = clubMapper.updateById(club);
        if (updateById<=0) return Result.send(StatusCode.UPDATE_CLUB_DESCRIPTION_ERROR,new SendMsg("更新社团简介失败"));
        return Result.success(new SingleCodeVO(ResultCode.UPDATE_CLUB_DESCRIPTION));
    }

    @Override
    public Object updateClubInfo(Club club) {
        Date date = new Date();
        club.setEstablishmentDate(date);
        int updateById = clubMapper.updateById(club);
        if (updateById<=0) return Result.send(StatusCode.UPDATE_CLUB_INFO_ERROR,new SendMsg("更新社团信息失败"));
        return Result.success(new SingleCodeVO(ResultCode.UPDATE_CLUB_INFO));
    }

    @Override
    public Object topTenClubs() {
        MPJLambdaWrapper<Club>  wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .orderByDesc(Club::getTotalMembers);
        try{
            List<Club> clubList = clubMapper.selectJoinList(Club.class, wrapper).stream().limit(10).collect(Collectors.toList());
            System.out.println(clubList);
            return  Result.success(new DataVO(ResultCode.GET_TOP_TEN_CLUB,clubList));
        }catch (Exception e){
            String exceptionAsString = e.toString();
            return Result.send(StatusCode.GET_TOP_TEN_CLUB_ERROR,new SendMsg(exceptionAsString));
        }
    }

    @Override
    public Object thisYearClubAnnual(Integer clubId, String declarationYear) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .select(Administrator::getDepartmentName)
                .select(Student::getStName,Student::getContact,Student::getPoliticalStatus)
                .leftJoin(Administrator.class,Administrator::getAdminId,Club::getResponsibleDepartmentId)
                .leftJoin(Student.class,Student::getStudentId,Club::getContactPersonId)
                .eq(Annualaudit::getClubId, clubId);
//                .eq(Annualaudit::getDeclarationYear, declarationYear);
        try{
            ClubAnnualDTO clubAnnualDTO = clubMapper.selectJoinOne(ClubAnnualDTO.class, wrapper);
//            clubAnnualDTO.setClubConstitutionAttachment(FileRequestUrlBuilder
//                    .buildFileRequestUrl(clubAnnualDTO.getClubConstitutionAttachment()));
//            clubAnnualDTO.setExternalSponsorshipAttachment(FileRequestUrlBuilder
//                    .buildFileRequestUrl(clubAnnualDTO.getExternalSponsorshipAttachment()));
//            clubAnnualDTO.setMeetingActivityListAttachment(FileRequestUrlBuilder
//                    .buildFileRequestUrl(clubAnnualDTO.getMeetingActivityListAttachment()));
            return Result.success(new DataVO(ResultCode.GET_THIS_YEAR_CLUB_ANNUL_INFO,clubAnnualDTO));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.GET_THIS_YEAR_CLUB_ANNUL_INFO_ERROR,new SendMsg("获取详细年审信息失败"));
        }
    }

    @Override
    public Object queryClubApplicationInfo(Integer clubId) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .select(Administrator::getDepartmentName)
                .select(Student::getStName,Student::getContact)
                .select(Clubapplicationrecord::getAttachmentUrl,
                        Clubapplicationrecord::getAdvisorResumeAttachmentUrl,
                        Clubapplicationrecord::getRecordId)
                .leftJoin(Administrator.class,Administrator::getAdminId,Club::getResponsibleDepartmentId)
                .leftJoin(Student.class,Student::getStudentId,Club::getContactPersonId)
                .leftJoin(Clubapplicationrecord.class,Clubapplicationrecord::getClubId,Club::getClubId)
                .eq(Club::getClubId,clubId);
        try{
            ClubApplicationInfoDTO clubApplicationInfoDTO = clubMapper.selectJoinOne(ClubApplicationInfoDTO.class, wrapper);
            String attachmentUrl = clubApplicationInfoDTO.getAttachmentUrl();
            clubApplicationInfoDTO.setAttachmentUrl(FileRequestUrlBuilder.buildFileRequestUrl(attachmentUrl));
            clubApplicationInfoDTO.setAdvisorResumeAttachmentUrl(FileRequestUrlBuilder
                    .buildFileRequestUrl(clubApplicationInfoDTO.getAdvisorResumeAttachmentUrl()));
            // 构造结果对象
            return Result.success(new DataVO(ResultCode.GET_CLUB_APPLICATION_INFO,clubApplicationInfoDTO));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.send(StatusCode.GET_CLUB_APPLICATION_INFO_ERROR,new SendMsg("获取详细社团申请信息失败"));
    }

    @Override
    public Boolean addClubTotalMember(Integer clubId) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .eq(Club::getClubId,clubId);
        Club clubInfo = clubMapper.selectJoinOne(Club.class, wrapper);
        Integer totalMembers = clubInfo.getTotalMembers();
        clubInfo.setTotalMembers(totalMembers+1);
        try{
            clubMapper.updateById(clubInfo);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object queryClubInfoInfo(Integer clubId) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .select(Administrator::getDepartmentName)
                .leftJoin(Administrator.class,Administrator::getAdminId,Club::getResponsibleDepartmentId)
                .eq(Club::getClubId,clubId);
        try{
            SingleClubInfoDTO singleClubInfoDTO = clubMapper.selectJoinOne(SingleClubInfoDTO.class, wrapper);
            singleClubInfoDTO.setImageUrl(FileRequestUrlBuilder
                    .buildFileRequestUrl(singleClubInfoDTO.getImageUrl()));
            return Result.success(new DataVO(ResultCode.GET_CLUB_DETAIL,singleClubInfoDTO));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_INFO_ERROR,new SendMsg("获取详细社团信息失败"));
        }
    }

    @Override
    public Object queryClubAnnualInfo(Integer declarationId) {
        MPJLambdaWrapper<Club> wrapper = new MPJLambdaWrapper<Club>()
                .selectAll(Club.class)
                .select(Annualaudit::getClubConstitutionAttachment,
                        Annualaudit::getExternalSponsorshipAttachment,
                        Annualaudit::getMeetingActivityListAttachment,
                        Annualaudit::getPublicityManagementInfo)
                .select(Administrator::getDepartmentName)
                .select(Student::getStName,Student::getContact,Student::getPoliticalStatus)
                .leftJoin(Administrator.class,Administrator::getAdminId,Club::getResponsibleDepartmentId)
                .leftJoin(Student.class,Student::getStudentId,Club::getContactPersonId)
                .leftJoin(Annualaudit.class,Annualaudit::getClubId,Club::getClubId)
                .eq(Annualaudit::getDeclarationId,declarationId);
        try{
            ClubAnnualDTO clubAnnualDTO = clubMapper.selectJoinOne(ClubAnnualDTO.class, wrapper);
            Object publicityManagementInfo = clubAnnualDTO.getPublicityManagementInfo();
            publicityManagementInfo = MyJsonParser.parserJsonText(publicityManagementInfo);
            clubAnnualDTO.setPublicityManagementInfo(publicityManagementInfo);
            clubAnnualDTO.setClubConstitutionAttachment(FileRequestUrlBuilder
                    .buildFileRequestUrl(clubAnnualDTO.getClubConstitutionAttachment()));
            clubAnnualDTO.setExternalSponsorshipAttachment(FileRequestUrlBuilder
                    .buildFileRequestUrl(clubAnnualDTO.getExternalSponsorshipAttachment()));
            clubAnnualDTO.setMeetingActivityListAttachment(FileRequestUrlBuilder
                    .buildFileRequestUrl(clubAnnualDTO.getMeetingActivityListAttachment()));
            System.out.println(clubAnnualDTO);
            return Result.success(new DataVO(ResultCode.GET_CLUB_ANNUL_INFO,clubAnnualDTO));
        }catch (Exception e){
            e.printStackTrace();
            return Result.send(StatusCode.GET_CLUB_ANNUL_ERROR,new SendMsg("获取详细年审信息失败"));
        }
    }

    @Override
    public List<Club> queryAdminClubInfo(Integer adminId) {
        QueryWrapper<Club> wrapper = new QueryWrapper<>();
        wrapper.eq("responsible_department_id", adminId);
        try{
            //Club club = clubMapper.selectOne(wrapper);
            List<Club> clubList = clubMapper.selectList(wrapper);
//            Club club = clubList.get(0);
            return clubList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean passClubApply(Integer clubId) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try{
            //System.out.println(clubId);
            Club club = clubMapper.selectById(clubId);
            club.setClubStatus(1);
            club.setEstablishmentDate(new Date());
            clubMapper.updateById(club);
            return true;
        }catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unPassClubApply(Integer clubId) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try{
            //System.out.println(clubId);
            Club club = clubMapper.selectById(clubId);
            club.setClubStatus(0);
            clubMapper.updateById(club);
            return true;
        }catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
        }
        return false;
    }
}




