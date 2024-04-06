package com.dinsaren.hrmanagementsystemapplication.configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointConfig.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String redirectUrl = httpServletRequest.getRequestURI();
        if (redirectUrl.contains("/user/reset")) {
            redirectUrl = "/";
        }
        httpServletRequest.getSession().setAttribute("REDIRECT_URL", redirectUrl);
        httpServletResponse.sendRedirect("/login");
    }
}
