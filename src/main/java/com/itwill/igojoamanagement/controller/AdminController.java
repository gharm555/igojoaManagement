package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.service.AdminService;
import com.itwill.igojoamanagement.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ReviewService reviewService;

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
