//package com.itwill.igojoamanagement.controller;
//
//import com.itwill.igojoamanagement.dto.PlaceNameDto;
//import com.itwill.igojoamanagement.service.PlaceService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//
//public class PlaceController {
//
//    private final PlaceService placeService;
//
//    @GetMapping("/detailPlaces")
//    public String getPlaces(@RequestParam(name="placeName") Model model) {
//        List<PlaceNameDto> placeNames = placeService.read();
//        log.info("placeNames: {}", placeNames);  // 로그 추가
//        model.addAttribute("placeNames", placeNames);
//        return "existingPlace";
//    }
//
//}
//
