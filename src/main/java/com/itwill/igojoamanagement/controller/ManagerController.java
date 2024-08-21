package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import com.itwill.igojoamanagement.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private final AdminService adminService;

    // 계정 생성 페이지
    @GetMapping("/signUp")
    public void signInPage() {
        log.info("signUp()");
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute Admin entity, @RequestParam(name = "roleId") Integer roleId, Model model) {
        if (entity.getAdminId() == null || entity.getAdminId().isEmpty()) {
            model.addAttribute("errorMessage", "사번(adminId)은 필수 입력 항목입니다.");
            return "manager/signUp";  // signUp 폼으로 다시 이동
        }
        Admin admin = adminService.create(entity, roleId);
        log.info("회원가입:{}", admin);
        return "redirect:/";
    }

    // 권한설정

}
