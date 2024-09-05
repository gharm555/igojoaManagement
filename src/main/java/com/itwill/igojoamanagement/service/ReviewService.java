package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.BlackUser;
import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.domain.key.UserBlackListPK;
import com.itwill.igojoamanagement.dto.ReportReviewDetailDto;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.ReviewRepository;
import com.itwill.igojoamanagement.repository.BlackUserRepository;
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
    private final BlackUserRepository blackUserRepository;

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

    // 신고 리뷰 삭제하기(신고, 리뷰 테이블 둘다 삭제)
    // TODO: 포인트 날리기
    @Transactional
    public void deleteReportReview(String logId) {
        ReportLog reportLog = reportLogRepository.findById(logId).orElseThrow();
        ReviewPK review = new ReviewPK(reportLog.getPlaceName(), reportLog.getReportedId());

        // 리뷰 테이블에서 삭제
        reviewRepository.deleteById(review);

        // 신고 테이블에서 삭제
        reportLogRepository.deleteById(logId);
    }

    // 신고 리뷰 취소하기 (신고 로그 테이블에서만 지우기)
    @Transactional
    public void cancelReportReview(String logId) {
        reportLogRepository.deleteById(logId);
    }

    // 부적절한 리뷰 삭제 (리뷰 테이블에서 지우기)
    // TODO: 포인트 날리기
    @Transactional
    public void deleteInappropriateReview(ReviewPK reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // 블랙리스트에 등록
    @Transactional
    public void addBlackList(BlackUser blackUser) {
        blackUserRepository.save(blackUser);
    }

    // 신고 리뷰 상세
    public ReportReviewDetailDto findReportReviewDetail(String logId) {
        ReportReviewDetailDto reviewDetail = reviewRepository.findReviewDetail(logId);
        return reviewDetail;
    }
}