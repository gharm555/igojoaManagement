//package com.itwill.igojoamanagement.repository;
//
//import com.itwill.igojoamanagement.domain.Review;
//import com.itwill.igojoamanagement.dto.ReportReviewDto;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.stereotype.Repository;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static com.itwill.igojoamanagement.domain.QReview.review;
//import static com.itwill.igojoamanagement.domain.QReportLog.reportLog;
//
//@Repository
//public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
//        super(Review.class);
//        this.queryFactory = queryFactory;
//    }
//
//    @Override
//    public Page<Review> findinappropriateReviews(Pageable pageable) {
//        JPAQuery<Review> query = queryFactory
//                .selectFrom(review)
//                .where(isInappropriate());
//
//        // 전체 개수를 위한 카운트 쿼리
//        JPAQuery<Long> totalQuery = queryFactory
//                .select(review.count())
//                .from(review)
//                .where(isInappropriate());
//
//        List<Review> results = query
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(review.modifiedAt.desc())
//                .fetch();
//
//        long total = totalQuery.fetchFirst() != null ? totalQuery.fetchFirst() : 0L;
//
//        return new PageImpl<>(results, pageable, total);
//
//    }
//
//    @Override
//    public Page<ReportReviewDto> findReportedReviews(Pageable pageable) {
//        JPAQuery<ReportReviewDto> query = queryFactory
//                .select(Projections.fields(ReportReviewDto.class,
//                        reportLog.reporterId,
//                        reportLog.reportedId,
//                        reportLog.reportedNickName,
//                        reportLog.placeName,
//                        reportLog.reportTime,
//                        reportLog.review,
//                        reportLog.reportReason))
//                .from(reportLog)
//                .where(reportLog.reasonCode.eq(101));
//
//        JPAQuery<Long> totalQuery = queryFactory
//                .select(reportLog.count())
//                .from(reportLog)
//                .where(reportLog.reasonCode.eq(101));
//
//        List<ReportReviewDto> results = query
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(reportLog.reportTime.asc())
//                .fetch();
//
//        long total = totalQuery.fetchFirst() != null ? totalQuery.fetchFirst() : 0L;
//
//        return new PageImpl<>(results, pageable, total);
//    }
//
////    private BooleanExpression isInappropriate() {
////        List<String> inappropriateKeywords = Arrays.asList("바보", "병신", "멍청이", "씨발");
////        BooleanExpression result = review.reviewContent.containsIgnoreCase(inappropriateKeywords.get(0));
////    }
//
//}
