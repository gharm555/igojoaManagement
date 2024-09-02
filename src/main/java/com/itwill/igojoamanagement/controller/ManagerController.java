package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final AdminService adminService;

    // 계정 생성 페이지
    @GetMapping("/signUp")
    public void signUp() {
        log.info("signUp()");
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute Admin entity) {
        Admin admin = adminService.create(entity);
        log.info("회원가입:{}", admin);
        return "redirect:/admin/signIn";
    }
}
