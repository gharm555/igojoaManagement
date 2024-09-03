package com.itwill.igojoamanagement.controller;

<<<<<<< HEAD
=======
import com.itwill.igojoamanagement.service.AdminService;
>>>>>>> 6abfd7180ea55685ce69b42bc98cab6b8de82b30
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

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