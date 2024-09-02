package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.RestrictionLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.domain.key.RestrictionLogPK;
import com.itwill.igojoamanagement.domain.key.ReviewPK;
import com.itwill.igojoamanagement.dto.ReportReviewDetailDto;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.ReviewRepository;
import com.itwill.igojoamanagement.repository.RestrictionLogRepository;
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
    private final RestrictionLogRepository restrictionLogRepository;

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

        // 제재 내역에 등록
        addRestrictionLog(reportLog);

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

    // 제재 내역에 등록
    @Transactional
    public void addRestrictionLog(ReportLog reportLog) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminId = authentication.getName();

        RestrictionLogPK restrictionLogPK = new RestrictionLogPK(reportLog.getReportedId());

        // 신고 사유에 따라 detail (리뷰, 닉네임)
        if (reportLog.getReasonCode() == 101) {
            RestrictionLog restrictionLog = RestrictionLog.builder()
                    .restrictionLogPK(restrictionLogPK)
                    .adminId(adminId).reasonCode(reportLog.getReasonCode()).detail(reportLog.getReview()).build();
            restrictionLogRepository.save(restrictionLog);
        } else {
            RestrictionLog restrictionLog = RestrictionLog.builder()
                    .restrictionLogPK(restrictionLogPK)
                    .adminId(adminId).reasonCode(reportLog.getReasonCode()).detail(reportLog.getReportedNickname()).build();
            restrictionLogRepository.save(restrictionLog);
        }


    }
    // 신고 리뷰 상세
    public ReportReviewDetailDto findReportReviewDetail(String logId) {
        ReportReviewDetailDto reviewDetail = reviewRepository.findReviewDetail(logId);
        return reviewDetail;
    }
}