package com.test.melnyk.internetshop.strategy;

import com.test.melnyk.internetshop.captcha.Captcha;
import com.test.melnyk.internetshop.captcha.CaptchaCreator;
import com.test.melnyk.internetshop.consts.Const;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_ERROR;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_IS_NOT_CORRECT_TRY_AGAIN;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_STORAGE;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TEXT;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TIMEOUT_MAP;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TIMEOUT_PARAM;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_USER_ID;
import static com.test.melnyk.internetshop.consts.Const.TIME_IS_EXPIRED;
import static com.test.melnyk.internetshop.consts.Const.UNIVERSALLY_UNIQUE_IDENTIFIER;

public class CaptchaContextStrategy implements CaptchaStrategy {

    @Override
    public Captcha setCaptchaStrategy(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext) {
        CaptchaCreator captchaCreator = new CaptchaCreator();
        Captcha captcha = captchaCreator.generateCaptcha();
        String uuid = httpServletRequest.getParameter(UNIVERSALLY_UNIQUE_IDENTIFIER);
        Cookie cookie = new Cookie(Const.CAPTCHA_USER_ID, uuid);
        httpServletResponse.addCookie(cookie);
        httpServletRequest.getServletContext().setAttribute(uuid, captcha);
        Map<String, String> captchaStorage = (Map<String, String>) servletContext.getAttribute(CAPTCHA_STORAGE);
        Map<String, LocalDateTime> captchaTimeout = (Map<String, LocalDateTime>) servletContext.getAttribute(CAPTCHA_TIMEOUT_MAP);
        String timeOut = servletContext.getInitParameter(CAPTCHA_TIMEOUT_PARAM);
        captchaStorage.put(uuid, String.valueOf(captcha));
        captchaTimeout.put(uuid, LocalDateTime.now().plusSeconds(Long.parseLong(timeOut)));
        return captcha;
    }

    @Override
    public void validate(Map<String, String> errors, HttpServletRequest httpServletRequest) {
        String uuid = getUUID(httpServletRequest);
        Captcha captcha = (Captcha) httpServletRequest.getServletContext().getAttribute(uuid);
        if (!captcha.getCaptchaCode().equals(httpServletRequest.getParameter(CAPTCHA_TEXT))) {
            errors.put(CAPTCHA_ERROR, CAPTCHA_IS_NOT_CORRECT_TRY_AGAIN);
        }
        Map<String, LocalDateTime> captchaTimeout = (Map<String, LocalDateTime>) httpServletRequest.getServletContext().getAttribute(CAPTCHA_TIMEOUT_MAP);
        if(Objects.isNull(captchaTimeout.get(uuid))){
            errors.put(CAPTCHA_ERROR, TIME_IS_EXPIRED);
        }
    }

    private String getUUID(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(Objects.nonNull(cookies)){
            for (Cookie cookie : cookies) {
                if (CAPTCHA_USER_ID.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
