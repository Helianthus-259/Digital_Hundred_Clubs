package com.szbt.authserver.service;

import java.io.IOException;

public interface VerifyCodeService {

    byte[] sendImageVerifyCode() throws IOException;
    boolean checkImageVerifyCode(String code);

    Object sendMailVerifyCode(String email);

    boolean checkMailVerifyCode(String verifyCode, String email);
}
