package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.captcha.Captcha;
import com.test.melnyk.internetshop.strategy.CaptchaStrategy;
import com.test.melnyk.internetshop.util.CaptchaExpiredTimeThread;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_STRATEGY;
import static com.test.melnyk.internetshop.consts.Const.IMAGE_PNG;
import static com.test.melnyk.internetshop.consts.Const.STRATEGY_MAP;

@WebServlet("/captcha_id")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strategyKey = req.getServletContext().getInitParameter(CAPTCHA_STRATEGY);
        Map<String, CaptchaStrategy> strategyMap = (Map<String, CaptchaStrategy>) req.getServletContext().getAttribute(STRATEGY_MAP);
        Captcha captcha = strategyMap.get(strategyKey).setCaptchaStrategy(req, resp, getServletContext());
        resp.setContentType(IMAGE_PNG);
        resp.getOutputStream().write(captcha.getImage());
        CaptchaExpiredTimeThread thread = new CaptchaExpiredTimeThread(getServletContext());
        thread.start();
    }
}