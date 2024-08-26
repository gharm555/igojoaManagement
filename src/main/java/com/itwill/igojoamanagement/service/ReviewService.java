package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReportLogRepository reportLogRepository;

    @Transactional(readOnly = true)
    public Page<Review> findInappropriateReviews(Pageable pageable) {
        Page<Review> reviewList = reviewRepository.findInappropriateReviews(pageable);
        log.info(reviewList.toString());
        return reviewList;
    }

    @Transactional(readOnly = true)
    public Page<ReportReviewDto> findReportReviews(PageRequest pageable) {
        Page<ReportReviewDto> reviewList = reviewRepository.findReportedReviews(pageable);
        log.info(reviewList.toString());
        reviewList.forEach(System.out::println);
        return reviewList;
    }

    // 신고 리뷰 삭제하기 (신고 로그 테이블에는 남겨두기?)
    public void deleteReportReview(ReviewPK reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // 신고 리뷰 취소하기 (신고 로그 테이블에서만 지우기)
    public void cancelReportReview(ReviewPK reviewId) {
        reportLogRepository.deleteById("아이디");
    }

    // 부적절한 리뷰 삭제 (리뷰 테이블에서 지우기)
    public void deleteInappropriateReview(ReviewPK reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
