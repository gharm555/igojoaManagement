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
import org.springframework.web.bind.annotation.*;

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
}
