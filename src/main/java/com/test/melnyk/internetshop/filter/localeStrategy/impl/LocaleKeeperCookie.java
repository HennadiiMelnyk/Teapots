package com.test.melnyk.internetshop.filter.localeStrategy.impl;

import com.test.melnyk.internetshop.filter.localeStrategy.LocaleKeeper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 *Save local strategy that saveLocale local in cookie.
 */
public class LocaleKeeperCookie implements LocaleKeeper {
    @Override
    public void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("locale", String.valueOf(locale));
       resp.addCookie(cookie);
    }

    @Override
    public Locale getLocale(HttpServletRequest httpServletRequest) {
        Locale locale = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("locale".equals(cookie.getName())) {
                cookie.setMaxAge(Integer.parseInt(httpServletRequest.getServletContext().getInitParameter("cookieAge")));
                return new Locale(cookie.getValue());
            }
        }
        return null;
    }
}
