package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @GetMapping("/signIn")
    public void signIn() {
        log.info("signIn");
    }


    @PostMapping("/signOut")
    public String signOut() {
        log.info("signOut");
        return "redirect:/";
    }

    @GetMapping("/emailVerify")
    public String verifyPage() {
        log.info("verify()");

        return "/admin/emailVerify";
    }

    @PostMapping("/emailVerify")
    public String verifyCode(@RequestParam String code, HttpSession session, Authentication authentication) {
        String verifyCode = (String) session.getAttribute("verificationCode");

        if(code.equals(verifyCode)) {
            Admin admin = (Admin) authentication.getPrincipal();
            if(admin.getRole().getRoleId() == 0) {
                return "redirect:/manager/adminManage";
            } else {
                return "redirect:/";
            }
        }
        return "redirect:/admin/emailVerify?error=true";
    }

    @GetMapping("/adminManage")
    @PreAuthorize("hasRole('ROLE_인사담당자')")
    public String adminManage() {
        return "/manager/adminManage";
    }



}
