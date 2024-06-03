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

    //3000-3999 student-server
    GET_STUDENT_INFO_ERROR("GET_STUDENT_INFO_ERROR",3001),
    UPDATE_STUDENT_INFO_ERROR("UPDATE_STUDENT_INFO_ERROR",3002),
    ADD_BACKBONE_EVALUATION_ERROR("ADD_BACKBONE_EVALUATION_ERROR",3003),

    //4000-4999 file-server
    UPLOAD_FILE_ERROR("UPLOAD_FILE_ERROR",4001),
    UPLOAD_IMAGE_ERROR("UPLOAD_IMAGE_ERROR",4002),

    //5000-5001 admin-server
    UPDATE_ADMIN_INFO_ERROR("UPDATE_ADMIN_INFO",5001),

    //6000-6999 activity-server
    ADD_ACTIVITY_ERROR("ADD_ACTIVITY_ERROR", 6001),

    ADD_ACTIVITY_PERFORMANCE_ERROR("ADD_ACTIVITY_PERFORMANCE_ERROR", 6002);

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
