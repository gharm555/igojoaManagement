package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.dto.AdminDto;
import com.itwill.igojoamanagement.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final HttpSession httpSession;

    @GetMapping("/signIn")
    public void signIn() {

        log.info("signIn");
    }
    @PostMapping("/signIn")
    public void login(@RequestParam String adminId, @RequestParam String password,
                      HttpSession session, HttpServletResponse response) throws IOException {

        Admin admin = adminService.signIn(adminId, password);

        if (adminId != null) {
            session.setAttribute("authenticatedAdmin", admin);
            response.sendRedirect("/"); // 로그인 성공 시 루트 경로로 리디렉션
        } else {
            response.sendRedirect("/signIn?error=true"); // 로그인 실패 시 로그인 페이지로 리디렉션
        }
    }

    @PostMapping("/signOut")
    public String signOut(HttpSession session) {
        log.info("signOut");
        session.invalidate();
        return "redirect:/";
    }


}
