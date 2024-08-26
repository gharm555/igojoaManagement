package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @DeleteMapping("/deleteReportReview")
    public ResponseEntity<String> deleteReportReview(@RequestBody String logId) {
        log.info("deleteReview(logId: {})", logId);

        reviewService.deleteReportReview(logId);

        return ResponseEntity.ok(logId); // 삭제한 댓글 아이디를 응답으로 보냄.
    }

    @DeleteMapping("/cancelReport")
    public ResponseEntity<String> cancelReport(@RequestBody String logId) {
        log.info("cancelReport(logId: {})", logId);

        reviewService.cancelReportReview(logId);

        return ResponseEntity.ok(logId);
    }

    @DeleteMapping("/deleteInappropriateReview")
    public ResponseEntity<String> deleteInappropriateReview(@RequestBody String placeName, @RequestBody String userId) {
        log.info("deleteInappropriateReview(placeName: {}, userId: {})", placeName, userId);

        ReviewPK review = new ReviewPK(placeName, userId);
        reviewService.deleteInappropriateReview(review);

        return ResponseEntity.ok(review.toString());
    }

}
