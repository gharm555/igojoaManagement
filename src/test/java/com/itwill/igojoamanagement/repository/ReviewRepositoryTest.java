package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.Domain.ReportLog;
import com.itwill.igojoamanagement.Domain.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@Slf4j
@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReportLogRepository reportLogRepository;

//    @Test
    public void reviewList() {
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("modifiedAt").descending());
        List<Review> reviews = reviewRepository.findAll();
        reviews.forEach(System.out::println);
    }

    @Test
    public void reportReviewList() {
        List<ReportLog> reportLogs = reportLogRepository.findAll();
        reportLogs.forEach(System.out::println);
    }
}
