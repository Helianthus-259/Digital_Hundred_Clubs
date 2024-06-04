package org.example.enums;

public enum ResultCode {
    //AUTH
    REGISTER_LOGIN(1),
    ADMIN_LOGIN(9),
    GET_IMAGE_VERIFY_CODE(39),
    GET_ENUM_LIST(41),

    //STUDENT
    GET_USER_INFO(3),
    UPDATE_USER_INFO(4),


    //CLUB
    GET_CLUB_INFO(2),
    ADD_CLUB_APPLICATION(5),
    GET_SINGLE_CLUB_ACTIVITY_NOTICE(6),
    GET_CLUB_INTRODUCTION(7),
    GET_ALL_CLUB_MEMBER(8),
    GET_CLUB_ACTIVITY_LIST(14),
    GET_CLUB_APPLY_LIST(15),
    AGREE_CLUB_APPLY(16),
    REJECT_CLUB_APPLY(17),
    GET_CLUB_EVALUATE_INFO(20),
    DELETE_CLUB_MEMBER(22),
    UPDATE_CLUB_MEMBER(23),
    ADD_CLUB_MEMBER(24),
    UPDATE_CLUB_INFO(25),
    GET_TOP_TEN_CLUB(32),
    ADD_CLUB_ANNUAL_AUDIT(34),
    ADD_CLUB_EVALUATION(35),
    GET_CLUB_NOTICE_LIST(36),
    ADD_NOTICE(37),
    ADD_MEETING(38),
    UPDATE_CLUB_DESCRIPTION(40),

    GET_CLUB_AWARDS(45),
    GET_CLUB_MEETINGS(46),
    ADD_CLUB_MEMBERSHIP_APPLICATION(47),



    //ACTIVITY
    GET_ACTIVITY_INFO(12),
    GET_ALL_ACTIVITY_INFO(13),
    ADD_ACTIVITY(18),
    ADD_PERSONAL_PERFORMANCE(29),
    ADD_activityPerformance(30),
    GET_LATEST_ACTIVITY(31),

    //ADMIN
    GET_ADMIN_INFO(10),
    UPDATE_ADMIN_INFO(11),
    ADD_BACKBONE_EVALUATION(19),
    GET_ALL_BACKBONE_EVALUATION(26),
    GET_ALL_CLUB_ANNUAL(27),
    GET_ALL_CLUB_EVALUATION(28),

    UPLOAD_IMAGE(21),
    UPLOAD_FILE(33);



    private final int code;


    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
