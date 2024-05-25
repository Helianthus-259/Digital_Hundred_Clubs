package com.szbt.authserver.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class SecurityUtil {

    //password为原始密码
    public static String encrypt(String password){
        String salt = UUID.randomUUID().toString().replace("-","");//利用UUID生成盐值,并且去掉其中的横杠.
        String finalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());//加密
        return salt+finalPassword;
    }
    //password为需要验证的密码
    //finalPassword为正确密码的加密值
    public static Boolean decrypt(String password,String finalPassword){
        if(!StringUtils.hasLength(password) || !StringUtils.hasLength(finalPassword) || finalPassword.length()!=64){
            return false;
        }
        //取回盐值
        String salt = finalPassword.substring(0,32);
        String newFinalPassword = DigestUtils.md5DigestAsHex((salt+password).getBytes());

        //此处要换成64位
        return (salt+newFinalPassword).equals(finalPassword);
    }
}