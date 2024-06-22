package com.szbt.authserver.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.szbt.authserver.service.VerifyCodeService;
import com.szbt.authserver.util.EmailUtil;
import com.szbt.authserver.util.ImageResponseUtils;
import com.szbt.authserver.util.SessionUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.example.enums.StatusCode;
import org.example.util.RedisKeyBuilder;
import org.example.util.Result;
import org.example.vo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Service
public class VerifyCodeImpl implements VerifyCodeService {

    @Autowired
    private EmailUtil emailUtil;

    @Resource(name = "captchaBean")
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 先用map存储已发送的验证码，已弃用转用redis
    private final Map<String, String> emailCodeMap = new ConcurrentHashMap<>(16);


    @Override
    public byte[] sendImageVerifyCode(){
        //证码字符串生成
        String text = defaultKaptcha.createText();
        System.out.println("text:" + text);
        //验证码存入session（可以将text加密储存）
        SessionUtil.session().setAttribute("key", text);
        //验证码转换
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            //定义响应值，写入byte
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ImageResponseUtils.buildImageRes(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkImageVerifyCode(String code) {
//        System.out.println("code："+code);
//        HttpSession session = SessionUtil.session();
//        String text = (String) session.getAttribute("key");
//        System.out.println("text："+text);
//        if (StringUtils.isBlank(text)) {
//            return false;
//            //return Result.send(StatusCode.VERIFY_IMAGE_CODE_ERROR,"图形验证码错误！");
//        }
//        if (!code.equals(text)) {
//            return false;
//            //return Result.send(StatusCode.VERIFY_IMAGE_CODE_ERROR,"图形验证码错误！");
//        }
//        //从session中移除验证码text
//        session.removeAttribute("key");
//        //return Result.success("图形验证码正确！");
        return true;
    }

    @Override
    public Object sendMailVerifyCode(String email) throws MessagingException {
        return Result.success(new SendMsg("发送成功"));//先直接成功
//        String verifyCode = emailUtil.generateCode (6);
//        if (emailUtil.sendMail(email,"数字百团验证服务",verifyCode))
//        {
//            // 保存验证码
//            //emailCodeMap.put(email, verifyCode);
//            //System.out.println(emailCodeMap.get(email));
//            //将验证码存入redis，有效期为5分钟
//            String emailKey = RedisKeyBuilder.generateEmailKey(email);
//            redisTemplate.opsForValue().set(emailKey, verifyCode, 300000, TimeUnit.MILLISECONDS);
//            String storedToken = (String) redisTemplate.opsForValue().get(emailKey);
//            System.out.println(storedToken);
//            return Result.success(new SendMsg("发送成功"));
//        }
//        return Result.send(StatusCode.SEND_VERIFY_CODE_ERROR,new SendMsg("发送失败"));
    }

    @Override
    public boolean checkMailVerifyCode(String verifyCode, String email) {
        return true;
        //return verifyCode.equals(emailCodeMap.get(email));
//        String emailKey = RedisKeyBuilder.generateEmailKey(email);
//        String storedToken = (String) redisTemplate.opsForValue().get(emailKey);
//        System.out.println(storedToken);
//        if(verifyCode.equals(storedToken)) return true;
//        else return false;
    }

}
