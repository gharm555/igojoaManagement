package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.dto.ReportedUserDto;
import com.itwill.igojoamanagement.dto.UserDto;
import com.itwill.igojoamanagement.service.GA4Service;
import com.itwill.igojoamanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class homeController {

    @Autowired
    private GA4Service ga4Service;

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();

        log.info("Logged in username: {}", username);
        log.info("Principal: {}", principal);

        try {
            Map<String, Object> ga4Data = ga4Service.getGA4Data();
            if (ga4Data != null) {
                model.addAttribute("ga4Data", ga4Data);
                return "index";
            } else {
                model.addAttribute("error", "No data available from GA4");
                return "index";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while retrieving GA4 data: " + e.getMessage());
            return "index";
        }

    }

    @GetMapping("/admin/user-management")
    public String getUserManagement(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀장"))
                    || auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀원"))) {
                Pageable pageable = PageRequest.of(page, size);
                Page<UserDto> userDtos = userService.getUsersIdPage(pageable);

                int totalPages = userDtos.getTotalPages();
                int currentPage = userDtos.getNumber();
                int startPage = Math.max(0, Math.min(currentPage - 2, totalPages - 5));
                int endPage = Math.min(startPage + 4, totalPages - 1);

                model.addAttribute("users", userDtos.getContent());
                model.addAttribute("currentPage", currentPage);
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("startPage", startPage);
                model.addAttribute("endPage", endPage);

                log.debug("컨트롤러 - 전체 페이지 수: {}", totalPages);
                log.debug("컨트롤러 - 현재 페이지: {}", currentPage);
                log.debug("컨트롤러 - 시작 페이지: {}", startPage);
                log.debug("컨트롤러 - 끝 페이지: {}", endPage);

                return "user/user :: user";
            } else {
                return "access-denied";
            }
        }

        return "access-denied";
    }

}
