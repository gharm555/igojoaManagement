package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.service.GA4Service;
import com.itwill.igojoamanagement.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class homeController {

    //    @GetMapping("/")
    //    public String home(Model model) {
    //        return "index";
    //    }

    @Autowired
    private GA4Service ga4Service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String home(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();

        getIndex(model);

        log.info("Logged in username: {}", username);
        log.info("Principal: {}", principal);

        try {
            Map<String, Object> ga4Data = ga4Service.getGA4Data();
            if (ga4Data != null) {
                model.addAttribute("ga4Data", ga4Data);
                return "index";
            } else {
//                logger.warn("GA4 data is null");
                model.addAttribute("error", "No data available from GA4");
                return "index";
            }
        } catch (Exception e) {
//            logger.error("Error retrieving GA4 data", e);
            model.addAttribute("error", "An error occurred while retrieving GA4 data: " + e.getMessage());
            return "index";
        }
    }


    @PreAuthorize("hasAnyAuthority('ROLE_리뷰_팀장', 'ROLE_리뷰_팀원')")
    public String getIndex(Model model) {
        List<Review> reviewList = reviewService.findInappropriateReviews().getContent();
        List<ReportReviewDto> reportLogList = reviewService.findReportReviews().getContent();

        log.info("신고리뷰{}", reviewList);
        log.info("부적절리뷰{}",reportLogList);


        model.addAttribute("reportReviews", reportLogList);
        model.addAttribute("inappropriateReviews",reviewList );

        return "index"; // index.html을 렌더링
    }
}
