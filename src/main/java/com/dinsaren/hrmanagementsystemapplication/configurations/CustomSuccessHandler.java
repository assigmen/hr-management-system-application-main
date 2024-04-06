package com.dinsaren.hrmanagementsystemapplication.configurations;

import com.dinsaren.hrmanagementsystemapplication.models.User;
import com.dinsaren.hrmanagementsystemapplication.services.AuthenticationUtilService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AuthenticationUtilService authenticationUtilService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AtomicReference<String> redirectUrl = new AtomicReference<>((String) httpServletRequest.getSession().getAttribute("REDIRECT_URL"));
        if (redirectUrl.get() != (null)) {
            User user = authenticationUtilService.checkUser();
            httpServletRequest.getSession().setAttribute("loggedInUserName", user.getUsername());
            httpServletRequest.getSession().setAttribute("profile", user.getProfile());
//            user.getRoles().forEach(role -> {
//                if (role.getName().equals("ROLE_ADMIN")) {
//                    redirectUrl.set("/dashboard");
//                } else {
//                    redirectUrl.set("/user");
//                }
//            });
        }
//        else {
//            redirectUrl.set("/");
//        }
        redirectUrl.set("/");
        httpServletResponse.sendRedirect(redirectUrl.get());
    }
}
