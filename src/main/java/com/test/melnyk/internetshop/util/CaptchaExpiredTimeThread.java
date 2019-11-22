package com.test.melnyk.internetshop.util;


import com.test.melnyk.internetshop.strategy.CaptchaStrategy;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_STORAGE;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TIMEOUT_MAP;

public class CaptchaExpiredTimeThread extends Thread {

    private ServletContext servletContext;
    private Map<String, LocalDateTime> captchaTimeout;
    private Map<String, CaptchaStrategy> captchaStorage;

    public CaptchaExpiredTimeThread(ServletContext servletContext) {
        this.servletContext = servletContext;
        captchaStorage = (Map<String, CaptchaStrategy>) servletContext.getAttribute(CAPTCHA_STORAGE);
        captchaTimeout = (Map<String, LocalDateTime>) servletContext.getAttribute(CAPTCHA_TIMEOUT_MAP);
    }

    @Override
    public void run() {
        boolean flag = false;
        while (!flag) {
            for (Map.Entry<String, LocalDateTime> entry : captchaTimeout.entrySet()) {
                LocalDateTime time = entry.getValue();
                if (time.isBefore(LocalDateTime.now())) {
                    captchaStorage.remove(entry.getKey());
                    captchaTimeout.remove(entry.getKey());
                    flag = true;
                }
            }
        }
    }
}
