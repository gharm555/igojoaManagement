package com.itwill.igojoamanagement.config;

import com.itwill.igojoamanagement.domain.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class EmailVerification implements AuthenticationSuccessHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String verificationCode = generateVerificationCode();
        HttpSession session = request.getSession();
        session.setAttribute("verificationCode", verificationCode);

        Admin admin = (Admin) authentication.getPrincipal();
        sendVerificationEmail(admin.getEmail(), verificationCode);

        response.sendRedirect("/admin/emailVerify");
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendVerificationEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("관리자 로그인 인증 코드");
        message.setText("인증 코드: " + code);
        javaMailSender.send(message);
    }
}
