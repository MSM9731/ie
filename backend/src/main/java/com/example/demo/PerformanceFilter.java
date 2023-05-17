package com.example.demo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class PerformanceFilter implements Filter {

    private static final String[] allowedOrigins = {
            "http://localhost:3000", "http://localhost:5500", "http://localhost:5501",
            "http://127.0.0.1:3000", "http://127.0.0.1:5500", "http://127.0.0.1:5501"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestOrigin = request.getHeader("Origin");
        if(true) {

            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
                    "GET, OPTIONS, HEAD, PUT, POST, DELETE");

            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            // CORS handshake (pre-flight request)
            if (request.getMethod().equals("OPTIONS")) {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                return;
            }
        }
        // pass the request along the filter chain
        filterChain.doFilter(request, servletResponse);
    }

    private boolean isAllowedOrigin(String origin){
        for (String allowedOrigin : allowedOrigins) {
            if(origin.equals(allowedOrigin)) return true;
        }
        return false;
    }
}
