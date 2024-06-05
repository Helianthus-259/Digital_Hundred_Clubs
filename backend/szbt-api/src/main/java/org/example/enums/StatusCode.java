package org.example.enums;

public enum StatusCode {
    SUCCESS("SUCCESS",200),
    BAD_REQUEST("BAD_REQUEST",400),
    UNAUTHORIZED("UNAUTHORIZED",401),
    FORBIDDEN("FORBIDDEN",403),
    NOT_FOUND("NOT_FOUND",404),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR",500),

    //1000-1999自定义异常
    MYSQL_ERROR("MYSQL_ERROR",1001),
    REDIS_ERROR("REDIS_ERROR",1002),

    //2000-2999 auth-server
    REGISTER_LOGIN_ERROR("REGISTER_LOGIN_ERROR",2001),
    GET_ENUM_LIST_ERROR("GET_ENUM_LIST_ERROR",2002),

    SEND_VERIFY_CODE_ERROR("SEND_VERIFY_CODE_ERROR",2002),
    VERIFY_MAIL_CODE_ERROR("VERIFY_MAIL_CODE_ERROR",2003),

    VERIFY_IMAGE_CODE_ERROR("VERIFY_IMAGE_CODE_ERROR",2004),
    SEND_IMAGE_CODE_ERROR("SEND_IMAGE_CODE_ERROR",2005),

    //3000-3999 student-server
    GET_STUDENT_INFO_ERROR("GET_STUDENT_INFO_ERROR",3001),
    UPDATE_STUDENT_INFO_ERROR("UPDATE_STUDENT_INFO_ERROR",3002),
    ADD_BACKBONE_EVALUATION_ERROR("ADD_BACKBONE_EVALUATION_ERROR",3003),

    //4000-4999 file-server
    UPLOAD_FILE_ERROR("UPLOAD_FILE_ERROR",4001),
    UPLOAD_IMAGE_ERROR("UPLOAD_IMAGE_ERROR",4002),

    //5000-5001 admin-server
    UPDATE_ADMIN_INFO_ERROR("UPDATE_ADMIN_INFO",5001),
    GET_ADMIN_INFO_ERROR("GET_ADMIN_INFO_ERROR",5002),
    GET_BACKBONE_EVALUATION_ERROR("GET_BACKBONE_EVALUATION_ERROR",5003),
    GET_All_ClUB_EVALUATION_ERROR("GET_All_ClUB_EVALUATION_ERROR",5004),
    GET_SINGLE_CLUB_BACK_BONE_ERROR("GET_SINGLE_CLUB_BACK_BONE_ERROR",5005),
    GET_SINGLE_CLUB_ANNUAL_ERROR("GET_SINGLE_CLUB_ANNUAL_ERROR",5006),
    GET_CLUB_ANNUAL_ERROR("GET_CLUB_ANNUAL_ERROR",5007),

    //6000-6999 activity-server
    ADD_ACTIVITY_ERROR("ADD_ACTIVITY_ERROR", 6001),
    ADD_ACTIVITY_PERFORMANCE_ERROR("ADD_ACTIVITY_PERFORMANCE_ERROR", 6002),
    JOIN_ACTIVITY_ERROR("JOIN_ACTIVITY_ERROR",6003),

    //7000-7999 club-server
    GET_CLUB_INTRODUCTION_ERROR("GET_CLUB_INTRODUCTION_ERROR",7001),
    GET_CLUB_ACT_AND_NTC_ERROR("GET_CLUB_ACT_AND_NTC_ERROR",7002),
    UPDATE_CLUB_INFO_ERROR("UPDATE_CLUB_INFO_ERROR",7003),
    ADD_MEETING_ERROR("ADD_CLUB_ACTIVITY_ERROR",7004),
    ADD_NOTICE_ERROR("ADD_NOTICE_ERROR",7005),
    ADD_CLUB_EVALUATION_ERROR("ADD_CLUB_EVALUATION_ERROR",7006),
    ADD_CLUB_ANNUAL_AUDIT_ERROR( "ADD_CLUB_ANNUAL_AUDIT_ERROR",7007),
    GET_CLUB_MEMBER_ERROR("GET_CLUB_MEMBER_ERROR",7008),
    UPDATE_CLUB_DESCRIPTION_ERROR("UPDATE_CLUB_DESCRIPTION_ERROR",7009),
    UPDATE_CLUB_MEMBER_ERROR( "UPDATE_CLUB_MEMBER_ERROR",7010),
    ADD_CLUB_MEMBER_ERROR( "ADD_CLUB_MEMBER_ERROR",7011),
    DELETE_CLUB_MEMBER_ERROR( "DELETE_CLUB_MEMBER_ERROR",7012),
    ADD_CLUB_APPLICATION_ERROR( "ADD_CLUB_APPLICATION_ERROR",7013),
    ADD_CLUB_MEMBERSHIP_APPLICATION_ERROR( "ADD_CLUB_MEMBERSHIP_APPLICATION_ERROR",7014),
    REJECT_CLUB_APPLY_ERROR( "REJECT_CLUB_APPLY_ERROR",7015),
    AGREE_CLUB_APPLY_ERROR( "AGREE_CLUB_APPLY_ERROR",7016),
    GET_CLUB_MEETINGS_ERROR( "GET_CLUB_MEETINGS_ERROR",7017),
    GET_CLUB_AWARDS_ERROR( "GET_CLUB_AWARDS_ERROR",7018),
    GET_CLUB_NOTICE_LIST_ERROR( "GET_CLUB_NOTICE_LIST_ERROR",7019),
    GET_TOP_TEN_CLUB_ERROR( "GET_TOP_TEN_CLUB_ERROR",7020),
    GET_CLUB_APPLICATION_ERROR("GET_CLUB_APPLICATION_ERROR",7021),
    GET_CLUB_ANNUL_ERROR("GET_CLUB_ANNUL_ERROR",7022),
    GET_CLUB_EVALUATION_ERROR("GET_CLUB_EVALUATION_ERROR",7023),
    GET_CLUB_INFO_ERROR("GET_CLUB_INFO_ERROR",7024);

    private final String name;
    private final int code;



    StatusCode(String name,int code) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
