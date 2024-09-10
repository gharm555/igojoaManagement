package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.QPlace;
import com.itwill.igojoamanagement.domain.QPlaceImage;
import com.itwill.igojoamanagement.dto.PlaceDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlaceQueryDslImpl extends QuerydslRepositorySupport implements PlaceQueryDsl {

    private final JPAQueryFactory queryFactory;

    public PlaceQueryDslImpl(JPAQueryFactory queryFactory) {
        super(Place.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<Object> findPlaceDetailWithImage(String placeName) {
        QPlace place = QPlace.place;
        QPlaceImage placeImage = QPlaceImage.placeImage;

        PlaceDTO result = queryFactory
                .select(Projections.constructor(PlaceDTO.class,
                        place.placeName,
                        place.largeAddress,
                        place.midiumAddress, // Note: This might need to be changed to 'mediumAddress' if the field name is corrected
                        place.smallAddress,
                        place.placeDescription,
                        place.placeLatitude,
                        place.placeLongitude,
                        place.operatingHours,
                        place.placeRadius,
                        placeImage.firstUrl,
                        placeImage.firstImgName,
                        placeImage.secondUrl,
                        placeImage.secondImgName,
                        placeImage.thirdUrl,
                        placeImage.thirdImgName))
                .from(place)
                .leftJoin(placeImage).on(place.placeName.eq(placeImage.placeName))
                .where(place.placeName.eq(placeName))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
