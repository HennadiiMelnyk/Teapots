package com.test.melnyk.internetshop.captcha;
/**
 * Captcha model
 */
public class Captcha {
    /**
     * digits code which printing on the PNG image
     */
    private String captchaCode;
    /**
     * Byte array that is a PNG image generated with text displayed.
     */
    private byte[] image;

    public Captcha(String captchaCode, byte[] image) {
        this.captchaCode = captchaCode;
        this.image = image;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
