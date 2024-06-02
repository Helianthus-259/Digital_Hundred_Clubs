package org.example.enums;

public enum StatusCode {
    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),

    //1000-1999自定义异常
    MYSQL_ERROR(1001),
    REDIS_ERROR(1002),

    //2000-2999 auth-server
    REGISTER_LOGIN_ERROR(2001),

    SEND_VERIFY_CODE_ERROR(2002),
    VERIFY_MAIL_CODE_ERROR(2003),

    VERIFY_IMAGE_CODE_ERROR(2003),

    //3000-3999 student-server
    GET_STUDENT_INFO_ERROR(3001),
    UPDATE_STUDENT_INFO_ERROR(3002),
    ADD_BACKBONE_EVALUATION_ERROR(3003),

    //
    UPLOAD_FILE_ERROR(4001),
    UPLOAD_IMAGE_ERROR(4002);

    private final int code;


    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
