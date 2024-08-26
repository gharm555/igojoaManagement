package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.domain.UserBlackList;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.domain.key.UserBlackListPK;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.ReviewRepository;
import com.itwill.igojoamanagement.repository.UserBlackListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReportLogRepository reportLogRepository;
    private final UserBlackListRepository userBlackListRepository;

    @Transactional(readOnly = true)
    public Page<Review> findInappropriateReviews() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Review> reviewList = reviewRepository.findInappropriateReviews(pageable);
        log.info(reviewList.toString());
        return reviewList;
    }

    @Transactional(readOnly = true)
    public Page<ReportReviewDto> findReportReviews() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ReportReviewDto> reviewList = reviewRepository.findReportedReviews(pageable);
        log.info(reviewList.toString());
        return reviewList;
    }

    // 신고 리뷰 삭제하기(신고, 리뷰 테이블 둘다 삭제)
    public void deleteReportReview(String logId) {
        ReportLog reportLog = reportLogRepository.findById(logId).orElseThrow();
        ReviewPK review = new ReviewPK(reportLog.getPlaceName(), reportLog.getReportedId());

        // 리뷰 테이블에서 삭제
        reviewRepository.deleteById(review);

        // 신고 테이블에서 삭제
        reportLogRepository.deleteById(logId);
    }

    // 신고 리뷰 취소하기 (신고 로그 테이블에서만 지우기)
    public void cancelReportReview(String logId) {
        reportLogRepository.deleteById(logId);
    }

    // 부적절한 리뷰 삭제 (리뷰 테이블에서 지우기)
    public void deleteInappropriateReview(ReviewPK reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // 블랙리스트에 등록
    public void addBlackList(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminId = authentication.getName();

        UserBlackListPK blackListLog = new UserBlackListPK(userId);
        UserBlackList userBlackList = UserBlackList.builder().userBlackListPK(blackListLog).reasonCode(101).adminId(adminId).build();

       userBlackListRepository.save(userBlackList);
    }
}
