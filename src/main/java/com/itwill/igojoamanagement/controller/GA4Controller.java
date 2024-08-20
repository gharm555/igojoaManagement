package com.itwill.igojoamanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.igojoamanagement.service.*;

import java.util.Map;

@Controller
public class GA4Controller {
    private static final Logger logger = LoggerFactory.getLogger(GA4Controller.class);

    @Autowired
    private GA4Service ga4Service;

    @GetMapping("/ga4-dashboard")
    public String ga4Dashboard(Model model) {
        try {
            Map<String, Object> ga4Data = ga4Service.getGA4Data();
            if (ga4Data != null) {
                model.addAttribute("ga4Data", ga4Data);
                return "ga4-dashboard";
            } else {
                logger.warn("GA4 data is null");
                model.addAttribute("error", "No data available from GA4");
                return "error";
            }
        } catch (Exception e) {
            logger.error("Error retrieving GA4 data", e);
            model.addAttribute("error", "An error occurred while retrieving GA4 data: " + e.getMessage());
            return "error";
        }
    }
}
