package org.example.config.myLogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //response.setStatus(HttpServletResponse.SC_OK);//TODO: 에러코드 변경 SC
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//

        response.getWriter().write("fail");
        log.info("로그인에 실패했습니다");
    }
}
