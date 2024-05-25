package com.szbt.authserver.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.code.kaptcha.Constants.*;

/**
 * 验证码配置参数
 *
 * @author LBF
 */
@Configuration
public class CaptchaConfig
{
    /**
     * 配置生成字符验证码的参数
     * @return
     */
    @Bean(name = "captchaBean")
    public DefaultKaptcha getKaptchaBean()
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 初始化一个Propertis对象，设定验证码参数
        Properties properties = new Properties();
        // 设置验证码图片宽度，默认为200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 设置验证码图片高度，默认为50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // 设置边框，默认有  yes/no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 设置验证码文本字符颜色，默认为Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // 验证码文本字符长度，默认为5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 验证码文本字体样式，默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        // 配置其参数
        Config config = new Config(properties);
        // 使验证码参数生效
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

