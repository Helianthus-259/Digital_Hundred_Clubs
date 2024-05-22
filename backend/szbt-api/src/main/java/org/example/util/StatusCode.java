package org.example.util;

public enum StatusCode {
    SUCCESS(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),

    REGISTER_LOGIN_ERROR(1001),

    SEND_VERIFY_CODE_ERROR(10086),

    VERIFY_IMAGE_CODE_ERROR(1003),

    UPLOAD_FILE_ERROR(1002);

    private final int code;


    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
