package com.szbt.authserver.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface VerifyCodeService {

    byte[] sendImageVerifyCode() throws IOException;
    boolean checkImageVerifyCode(String code);

    Object sendMailVerifyCode(String email) throws MessagingException;

    boolean checkMailVerifyCode(String verifyCode, String email);
}
