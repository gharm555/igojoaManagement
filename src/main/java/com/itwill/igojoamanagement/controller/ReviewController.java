package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping("/reportReview")
    public ResponseEntity<Map<String,Object>> getReportReview(@RequestParam(defaultValue = "0") int page) {

        int size = 10;
        Page<ReportReviewDto> reportLogList = reviewService.findReportReviews(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        if(reportLogList == null || reportLogList.isEmpty()) {
            response.put("message", "신고된 리뷰가 없습니다.");
            return ResponseEntity.ok(response);
        }

        response.put("reportLogList", reportLogList);
        response.put("currentPage", reportLogList.getNumber());
        response.put("totalPages", reportLogList.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inappropriateReview")
    public ResponseEntity<Map<String,Object>> getInappropriateReview(@RequestParam(defaultValue = "0") int page) {
        int size = 10;
        Page<Review> reviewList = reviewService.findInappropriateReviews(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        if(reviewList == null || reviewList.isEmpty()) {
            response.put("message", "부적절한 리뷰가 없습니다.");
            return ResponseEntity.ok(response);
        }
        response.put("reviewList", reviewList);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteReportReview")
    public ResponseEntity<String> deleteReportReview(@RequestBody Map<String, Object> logId ) {
        log.info("deleteReview(logId: {})", logId);
        String reviewLog = (String)logId.get("logId");
        reviewService.deleteReportReview(reviewLog);

        return ResponseEntity.ok(reviewLog); // 삭제한 댓글 아이디를 응답으로 보냄.
    }

    @DeleteMapping("/cancelReport")
    public ResponseEntity<String> cancelReport(@RequestBody Map<String, Object> logId) {
        log.info("cancelReport(logId: {})", logId);
        String reviewLog = (String)logId.get("logId");

        reviewService.cancelReportReview(reviewLog);

        return ResponseEntity.ok(reviewLog);
    }

    @DeleteMapping("/deleteInappropriateReview")
    public ResponseEntity<?> deleteInappropriateReview(@RequestBody Map<String, Object> data) {

        String placeName = (String)data.get("placeName");
        String userId = (String)data.get("reportedId");
        log.info("deleteInappropriateReview(placeName: {}, reportedId: {})", placeName, userId);

        ReviewPK review = new ReviewPK(placeName, userId);
        reviewService.deleteInappropriateReview(review);

        return ResponseEntity.ok(review);
    }

}
