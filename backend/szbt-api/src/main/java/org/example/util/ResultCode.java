package org.example.util;

public enum ResultCode {
    REGISTER_LOGIN(1),
    CLUB_INFO(2),
    USER_INFO(3),
    UPDATE_USER_INFO(4),
    CLUB_APPLICATION(5),
    CLUB_ACTIVITY(6),
    UPLOAD_FILE_ERROR(7);

    private final int code;


    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
