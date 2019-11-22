package com.test.melnyk.internetshop.filter.localeStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Strategy for saveLocale user local in cookie or session.
 */
public interface LocaleKeeper {

    /**
     * Save user local in this app.
     *
     * @param locale - current local
     * @param req    - HttpServletRequest request
     * @param resp   - HttpServletResponse response
     */
    void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse resp);
    Locale getLocale(HttpServletRequest httpServletRequest);

}
