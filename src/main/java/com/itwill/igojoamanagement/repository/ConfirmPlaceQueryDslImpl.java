package com.itwill.igojoamanagement.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.QConfirmPlace;
import com.itwill.igojoamanagement.domain.QPlaceImage;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceSoochangDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;



import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ConfirmPlaceQueryDslImpl extends QuerydslRepositorySupport implements ConfirmPlaceQueryDsl {

    private final JPAQueryFactory queryFactory;

    public ConfirmPlaceQueryDslImpl(JPAQueryFactory queryFactory) {
        super(ConfirmPlace.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public void deleteByPlaceNameAndReporterId(String placeName, String reporterId) {
        QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;

        long deletedCount = queryFactory
                .delete(confirmPlace)
                .where(confirmPlace.placeName.eq(placeName)
                        .and(confirmPlace.reporterId.eq(reporterId)))
                .execute();

        if (deletedCount == 0) {
            throw new IllegalArgumentException("No ConfirmPlace found for deletion with placeName: " + placeName + " and reporterId: " + reporterId);
        }

    }
    @Override
    public void deleteByPlaceNameAndReporterIdAndImg(String placeName, String reporterId) {
        QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;
        QPlaceImage placeImage = QPlaceImage.placeImage;
        long imageDeletedCount = queryFactory
                .delete(placeImage)
                .where(placeImage.placeName.eq(placeName))
                .execute();

        long deletedCount = queryFactory
                .delete(confirmPlace)
                .where(confirmPlace.placeName.eq(placeName)
                        .and(confirmPlace.reporterId.eq(reporterId)))
                .execute();

        if (deletedCount == 0) {
            throw new IllegalArgumentException("No ConfirmPlace found for deletion with placeName: " + placeName + " and reporterId: " + reporterId);
        }
    }

    @Override
    public Optional<ConfirmPlaceDetailsDTO> findConfirmPlaceDetailsWithImages(String placeName, String reporterId) {
        log.info("Querying for placeName: {}, reporterId: {}", placeName, reporterId);
        if (placeName == null || reporterId == null) {
            throw new IllegalArgumentException("placeName and reporterId must not be null");
        }
        QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;
        QPlaceImage placeImage = QPlaceImage.placeImage;

        ConfirmPlaceDetailsDTO result = queryFactory
                .select(Projections.constructor(ConfirmPlaceDetailsDTO.class,
                        confirmPlace.placeName,
                        confirmPlace.reporterId,
                        confirmPlace.largeAddress,
                        confirmPlace.mediumAddress,
                        confirmPlace.smallAddress,
                        confirmPlace.placeDescription,
                        confirmPlace.placeLongitude,
                        confirmPlace.placeLatitude,
                        confirmPlace.operatingHours,
                        confirmPlace.radius,
                        placeImage.firstUrl,
                        placeImage.firstImgName,
                        placeImage.secondUrl,
                        placeImage.secondImgName,
                        placeImage.thirdUrl,
                        placeImage.thirdImgName))
                .from(confirmPlace)
                .leftJoin(placeImage).on(confirmPlace.placeName.eq(placeImage.placeName))
                .where(confirmPlace.placeName.eq(placeName)
                        .and(confirmPlace.reporterId.eq(reporterId)))
                .fetchOne();

        log.info("Result for placeName: {}, reporterId: {}: {}", placeName, reporterId, result);
        return Optional.ofNullable(result);
    }
    @Override
    public long updateConfirmPlace(ConfirmPlace updateConfirmPlace) {
        if (queryFactory == null) {
            throw new IllegalStateException("QueryFactory is not initialized");
        }

        QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;

        JPAUpdateClause updateClause = queryFactory.update(confirmPlace);

        // 모든 필드를 업데이트 (null 값 포함)
        updateClause.set(confirmPlace.largeAddress, updateConfirmPlace.getLargeAddress())
                .set(confirmPlace.mediumAddress, updateConfirmPlace.getMediumAddress())
                .set(confirmPlace.smallAddress, updateConfirmPlace.getSmallAddress())
                .set(confirmPlace.placeDescription, updateConfirmPlace.getPlaceDescription())
                .set(confirmPlace.placeLongitude, updateConfirmPlace.getPlaceLongitude())
                .set(confirmPlace.placeLatitude, updateConfirmPlace.getPlaceLatitude())
                .set(confirmPlace.operatingHours, updateConfirmPlace.getOperatingHours())
                .set(confirmPlace.radius, updateConfirmPlace.getRadius())
                .set(confirmPlace.displayDate, LocalDateTime.now());

        return updateClause
                .where(confirmPlace.placeName.eq(updateConfirmPlace.getPlaceName())
                        .and(confirmPlace.reporterId.eq(updateConfirmPlace.getReporterId())))
                .execute();
    }



}







