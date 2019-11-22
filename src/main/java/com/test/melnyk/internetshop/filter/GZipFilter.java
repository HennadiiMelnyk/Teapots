package com.test.melnyk.internetshop.filter;

import com.test.melnyk.internetshop.filter.gzip.GZipServletResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZipFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest  = (HttpServletRequest)  servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (acceptsGZipEncoding(httpRequest) ) {
            httpResponse.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse =
                    new GZipServletResponseWrapper(httpResponse);
            filterChain.doFilter(servletRequest, gzipResponse);
            gzipResponse.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader("Accept-Encoding");

        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }
}
