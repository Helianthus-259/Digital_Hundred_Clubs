package com.szbt.clubserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Club;
import org.example.entity.ClubMembershipApplication;

/**
* @author 小壳儿
* @description 针对表【club_membership_application】的数据库操作Service
* @createDate 2024-06-04 11:24:18
*/
public interface ClubMembershipApplicationService extends IService<ClubMembershipApplication> {
    Object queryClubApplyList(Integer clubId);

    Object joinClub(ClubMembershipApplication clubMembershipApplication);

    Object agreeClubApply(Club club, Integer studentId);

    Object rejectClubApply(Integer clubId, Integer studentId, String rejectReason);
}
