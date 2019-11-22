package com.test.melnyk.internetshop.filter;

import com.test.melnyk.internetshop.filter.security.SecurityXml;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Path.INDEX_PAGE;

public class SecurityFilter implements Filter {
    private Map<String, List<String>> accessMap;
    private String[] paths = new String[]{"/css", "/images", "/js", "/jspFragments", ".jsp"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            File file = new File(String.valueOf(filterConfig.getServletContext().getAttribute("security")));
            JAXBContext jaxbContext = JAXBContext.newInstance(SecurityXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SecurityXml securityXml = (SecurityXml) unmarshaller.unmarshal(file);
            accessMap = securityXml.getSecurityMap();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String servletPath = httpServletRequest.getServletPath();
        if (checkInput(servletPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (checkAccess(servletPath, httpServletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendRedirect(INDEX_PAGE);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean checkAccess(String s, HttpServletRequest httpServletRequest) {
        String action = s.substring(1);
        String userRole = (String) httpServletRequest.getServletContext().getAttribute("userRole");
        List<String> availableActions = accessMap.get(userRole);
        return availableActions.contains(action);
    }

    private boolean checkInput(String path) {
        return Arrays.stream(paths).anyMatch(path::contains);
    }
}
