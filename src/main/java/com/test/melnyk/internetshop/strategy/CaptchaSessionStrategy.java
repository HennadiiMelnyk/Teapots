package com.test.melnyk.internetshop.strategy;
import com.test.melnyk.internetshop.captcha.Captcha;
import com.test.melnyk.internetshop.captcha.CaptchaCreator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_ERROR;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_IS_NOT_CORRECT_TRY_AGAIN;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_SESSION;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_SESSION_VALUE;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TEXT;

public class CaptchaSessionStrategy implements CaptchaStrategy{

    @Override
    public Captcha setCaptchaStrategy(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext) {
        CaptchaCreator captchaCreator = new CaptchaCreator();
        Captcha captcha = captchaCreator.generateCaptcha();
        httpServletRequest.getSession().setAttribute(CAPTCHA_SESSION, captcha);
        return captcha;
    }

    @Override
    public void validate(Map<String, String> errors, HttpServletRequest httpServletRequest) {
        String originCaptcha = (String) httpServletRequest.getSession().getAttribute(CAPTCHA_SESSION_VALUE);
        if (!originCaptcha.equals(httpServletRequest.getParameter(CAPTCHA_TEXT))) {
            errors.put(CAPTCHA_ERROR, CAPTCHA_IS_NOT_CORRECT_TRY_AGAIN);
        }
    }
}

