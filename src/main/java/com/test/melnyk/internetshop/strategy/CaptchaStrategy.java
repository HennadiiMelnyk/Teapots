package com.test.melnyk.internetshop.strategy;


import com.test.melnyk.internetshop.captcha.Captcha;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface CaptchaStrategy {

    Captcha setCaptchaStrategy(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext);

    void validate(Map<String, String> errors, HttpServletRequest httpServletRequest);

}

