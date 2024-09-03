package com.itwill.igojoamanagement.repository;


import java.util.Optional;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.QConfirmPlace;
import com.itwill.igojoamanagement.domain.QPlaceImage;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;



import org.springframework.stereotype.Repository;

@Repository
public class ConfirmPlaceQueryDslImpl extends QuerydslRepositorySupport implements ConfirmPlaceQueryDsl {

    private final JPAQueryFactory queryFactory;

    public ConfirmPlaceQueryDslImpl( JPAQueryFactory queryFactory) {
        super(ConfirmPlace.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public void deleteByPlaceNameAndReporterId(String placeName, String reporterId) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Object> findConfirmPlaceDetailsWithImages(String placeName, String reporterId) {
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

        return Optional.ofNullable(result);
    }
}
