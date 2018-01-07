package com.nmg.teach.core.util;

import com.nmg.teach.config.properties.TeachProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     *
     * @author nmg
     * @Date 2017/5/23 22:34
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(TeachProperties.class).getKaptchaOpen();
    }
}