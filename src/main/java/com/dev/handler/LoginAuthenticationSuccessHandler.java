package com.dev.handler;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Getter
    private String forwardUrl;

    @Resource
    private RedirectStrategy redirectStrategy;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("username", authentication.getName());

        }
        redirectStrategy.sendRedirect(request, response, getForwardUrl());

    }


    @Resource
    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

}
