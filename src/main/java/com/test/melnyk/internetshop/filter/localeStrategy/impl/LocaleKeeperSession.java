package com.test.melnyk.internetshop.filter.localeStrategy.impl;

import com.test.melnyk.internetshop.filter.localeStrategy.LocaleKeeper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Save local strategy that saveLocale local in session.
 */
public class LocaleKeeperSession implements LocaleKeeper {
    @Override
    public void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("locale", locale);
    }

    @Override
    public Locale getLocale(HttpServletRequest httpServletRequest) {
        return (Locale) httpServletRequest.getSession().getAttribute("locale");
    }
}
