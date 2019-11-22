package com.test.melnyk.internetshop.filter;

import com.test.melnyk.internetshop.filter.localeStrategy.LocaleKeeper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LocaleFilter implements Filter {

    private Locale defaultLocale;
    private Set<Locale> availableLocales;
    private LocaleKeeper localeKeeper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeKeeper = (LocaleKeeper) filterConfig.getServletContext().getAttribute("localeKeeper");
        Map<String, String> languages = new HashMap<>();
        availableLocales = new HashSet<>();
        String defaultInitLocale = filterConfig.getInitParameter("default");
        defaultLocale = isValid(defaultInitLocale) ? new Locale(defaultInitLocale) : Locale.US;
        String[] locales = filterConfig.getInitParameter("locales").split(";");
        Arrays.stream(locales)
                .filter(this::isValid)
                .forEach(str -> {
                    Locale locale = new Locale(str);
                    availableLocales.add(locale);
                    languages.put(str, locale.getDisplayLanguage());
                });
        filterConfig.getServletContext().setAttribute("languages", languages);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        servletRequest = getRequestWrapper(httpServletRequest, httpServletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    private boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        Locale locale = new Locale(str);
        return locale.getLanguage() != null && !locale.getLanguage().isEmpty();
    }

    private Locale getDefaultLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Locale result = defaultLocale;
        Enumeration<Locale> locales = httpServletRequest.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (availableLocales.contains(locale)) {
                result = locale;
                break;
            }
        }
        saveLocale(httpServletRequest, httpServletResponse, result);
        return result;
    }

    private void saveLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        localeKeeper.saveLocale(locale, httpServletRequest, httpServletResponse);
    }

    private HttpServletRequestWrapper getRequestWrapper(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Locale locale = getLocale(httpServletRequest, httpServletResponse);
        return new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public Locale getLocale() {
                locale.getLanguage();
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return Collections.enumeration(Collections.singletonList(locale));
            }
        };
    }

    private Locale getLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        checkNewLocale(httpServletRequest, httpServletResponse);
        Locale locale = localeKeeper.getLocale(httpServletRequest);
        if (locale != null) {
            return locale;
        }
        return getDefaultLocale(httpServletRequest, httpServletResponse);
    }

    private void checkNewLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String lang = httpServletRequest.getParameter("lang");
        if (lang != null && !"".equals(lang)) {
            Locale newLocale = new Locale(lang);
            saveLocale(httpServletRequest, httpServletResponse, newLocale);
        }
    }
}
