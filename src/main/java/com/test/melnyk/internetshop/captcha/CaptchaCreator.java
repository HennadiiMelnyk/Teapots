package com.test.melnyk.internetshop.captcha;

import java.util.Random;

public class CaptchaCreator {

    public Captcha generateCaptcha() {
        Random random = new Random(System.currentTimeMillis());
        String captchaString = String.format("%04d", random.nextInt(9999));
        byte[] image = org.javalite.activeweb.Captcha.generateImage(captchaString);
        return new Captcha(captchaString, image);
    }
}