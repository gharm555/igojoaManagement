package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.dto.PlaceNameDto;
import com.itwill.igojoamanagement.service.GA4Service;
import com.itwill.igojoamanagement.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
@Controller
public class homeController {
//    @GetMapping("/")
//    public String home(Model model) {
//        return "index";
//    }

    @Autowired
    private GA4Service ga4Service;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/")
    public String home(Model model) {
        try {
            loadPlaceData(model);
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
        private void loadPlaceData(Model model) {
            try {
                List<PlaceNameDto> placeNames = placeService.read();
                model.addAttribute("placeNames", placeNames);
            } catch (Exception e) {
                model.addAttribute("placeError", "Error retrieving place data: " + e.getMessage());
            }
        }
    }

