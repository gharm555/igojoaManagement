package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.BlackUser;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.dto.ReportReviewDetailDto;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 신고 리뷰
    @GetMapping("/reportReview")
    public ResponseEntity<Map<String, Object>> getReportReview(@RequestParam(defaultValue = "0") int page) {

        int size = 10;
        Page<ReportReviewDto> reportLogList = reviewService.findReportReviews(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        if (reportLogList == null || reportLogList.isEmpty()) {
            response.put("message", "신고된 리뷰가 없습니다.");
            return ResponseEntity.ok(response);
        }

        response.put("reportLogList", reportLogList);
        response.put("currentPage", reportLogList.getNumber());
        response.put("totalPages", reportLogList.getTotalPages());
        return ResponseEntity.ok(response);
    }

    // 부적절한 리뷰
    @GetMapping("/inappropriateReview")
    public ResponseEntity<Map<String, Object>> getInappropriateReview(@RequestParam(defaultValue = "0") int page) {
        int size = 10;
        Page<Review> reviewList = reviewService.findInappropriateReviews(PageRequest.of(page, size));
        Map<String, Object> response = new HashMap<>();
        if (reviewList == null || reviewList.isEmpty()) {
            response.put("message", "부적절한 리뷰가 없습니다.");
            return ResponseEntity.ok(response);
        }
        response.put("reviewList", reviewList);
        return ResponseEntity.ok(response);
    }

    // 신고리뷰 삭제
    @DeleteMapping("/deleteReportReview")
    public ResponseEntity<String> deleteReportReview(@RequestBody Map<String, Object> logId) {
        log.info("deleteReview(logId: {})", logId);
        String reviewLog = (String) logId.get("logId");
        reviewService.deleteReportReview(reviewLog);

        return ResponseEntity.ok(reviewLog); // 삭제한 댓글 아이디를 응답으로 보냄.
    }

    // 신고 취소
    @DeleteMapping("/cancelReport")
    public ResponseEntity<String> cancelReport(@RequestBody Map<String, Object> logId) {
        log.info("cancelReport(logId: {})", logId);
        String reviewLog = (String) logId.get("logId");

        reviewService.cancelReportReview(reviewLog);

        return ResponseEntity.ok(reviewLog);
    }

    // 부적절한 리뷰 삭제
    @DeleteMapping("/deleteInappropriateReview")
    public ResponseEntity<?> deleteInappropriateReview(@RequestBody Map<String, Object> data) {
        log.info("deleteInappropriateReview(data: {})", data);

        String placeName = (String) data.get("placeName");
        String userId = (String) data.get("reportedId");
        log.info("deleteInappropriateReview(placeName: {}, userId: {})", placeName, userId);

        ReviewPK review = new ReviewPK(placeName, userId);
        reviewService.deleteInappropriateReview(review);

        return ResponseEntity.ok(review);
    }

    // 리뷰 상세
    @GetMapping("/detail")
    public ResponseEntity<ReportReviewDetailDto> getDetail(@RequestParam String logId) {
        log.info("getDetail(logId: {})", logId);

        ReportReviewDetailDto reviewDetail = reviewService.findReportReviewDetail(logId);

        return ResponseEntity.ok(reviewDetail);
    }

    // 블랙리스트 등록
    @PostMapping("/userBlack")
    public ResponseEntity<?> userBlack(@RequestBody Map<String, Object> requestData) {
        log.info("blackList(data: {})", requestData);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> data = (Map<String, Object>) requestData.get("data");
        String userId = (String) data.get("userId");
        String detail = (String) data.get("detail");

        log.info("userBlack(userId: {}, detail: {})", userId, detail);

        // 이미 블랙리스트에 등록된 유저인지 확인
        if((reviewService.isUserBlacklisted(userId)).equals("블랙리스트")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "이미 블랙리스트에 등록된 유저입니다."));
        }

        String adminId = authentication.getName();

        BlackUser blackUser = BlackUser.builder().userId(userId).adminId(adminId).reasonCode(101).detail(detail).confirm("블랙리스트").build();

        reviewService.addBlackList(blackUser);

        return ResponseEntity.ok(blackUser);
    }
}