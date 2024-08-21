package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.Review;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReportLogRepository reportLogRepository;

    @Transactional(readOnly = true)
    public Page<Review> findAllReviewList() {
        log.info("findAllReviewList");

        Pageable pageable = PageRequest.of(0, 10, Sort.by("modifiedAt").descending());
        Page<Review> reviewList = reviewRepository.findAll(pageable);

        return reviewList;
    }

    @Transactional(readOnly = true)
    public Page<ReportLog> findAllReportLogList() {
        log.info("findAllReportLogList");

        Pageable pageable = PageRequest.of(0, 10, Sort.by("reportTime").descending());
        Page<ReportLog> reportLogList = reportLogRepository.findAll(pageable);

        return reportLogList;
    }
}
