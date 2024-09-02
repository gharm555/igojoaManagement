package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@RequestParam String adminId, @RequestParam String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Login attempt for adminId: {}", adminId);
        try {
            Admin admin = adminService.signIn(adminId, password);
            session.setAttribute("authenticatedAdmin", admin);
            return "redirect:/index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "로그인에 실패했습니다.");
            return "redirect:/admin/signIn";
        }
    }


    @PostMapping("/signOut")
    public String signOut(HttpSession session) {
        log.info("signOut");
        session.invalidate();
        return "redirect:/";
    }


}
