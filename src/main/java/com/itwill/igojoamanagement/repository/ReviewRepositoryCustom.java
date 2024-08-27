package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.dto.ReportReviewDetailDto;
import com.itwill.igojoamanagement.dto.ReportReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<Review> findInappropriateReviews(Pageable pageable);

    Page<ReportReviewDto> findReportedReviews(Pageable pageable);

    ReportReviewDetailDto findReviewDetail(String logId);
}
