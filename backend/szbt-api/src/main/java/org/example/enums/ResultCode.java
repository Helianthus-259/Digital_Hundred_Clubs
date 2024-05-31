package org.example.enums;

public enum ResultCode {
    REGISTER_LOGIN(1),
    CLUB_INFO(2),
    USER_INFO(3),
    UPDATE_USER_INFO(4),
    CLUB_APPLICATION(5),
    CLUB_ACTIVITY(6),
    UPLOAD_FILE_ERROR(7),

    ADD_BACKBONE_EVALUATION(19),

    GET_ALL_BACKBONE_EVALUATION(26),

    ADMIN_LOGIN(9);



    private final int code;


    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
