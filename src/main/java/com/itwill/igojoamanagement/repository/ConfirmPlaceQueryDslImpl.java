package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.QConfirmPlace;
import com.itwill.igojoamanagement.domain.QPlaceImage;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;


public class ConfirmPlaceQueryDslImpl implements ConfirmPlaceQueryDsl {
	private final JPAQueryFactory queryFactory;

	  public ConfirmPlaceQueryDslImpl(JPAQueryFactory queryFactory) {

          this.queryFactory = queryFactory;
      }



	@Override
	public List<ConfirmPlaceNameDto> findAllConfirmPlaceNames() {
		QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;

		return queryFactory
				.select(Projections.constructor(ConfirmPlaceNameDto.class,
						confirmPlace.placeName))
				.from(confirmPlace)
				.fetch();
	}


	@Override
	public Optional<ConfirmPlaceDetailsDTO> findPlaceDetailsWithImages(String placeName, String reporterId) {
		QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;
		QPlaceImage placeImage = QPlaceImage.placeImage;

		return Optional.ofNullable(queryFactory
				.select(Projections.constructor(ConfirmPlaceDetailsDTO.class,
						confirmPlace.placeName,
						confirmPlace.largeAddress,
						confirmPlace.mediumAddress,
						confirmPlace.smallAddress,
						confirmPlace.placeDescription,
						confirmPlace.placeLongitude,
						confirmPlace.placeLatitude,
						confirmPlace.operatingHours,
						confirmPlace.radius,
						placeImage.firstUrl,
						placeImage.secondUrl,
						placeImage.thirdUrl))
				.from(confirmPlace)
				.leftJoin(placeImage).on(confirmPlace.placeName.eq(placeImage.placeName))
				.where(confirmPlace.placeName.eq(placeName)
						.and(confirmPlace.reporterId.eq(reporterId)))
				.fetchOne());
	}

	@Override
	public void deleteByPlaceNameAndReporterId(String placeName, String reporterId) {
		QConfirmPlace confirmPlace = QConfirmPlace.confirmPlace;

		queryFactory.delete(confirmPlace)
				.where(confirmPlace.placeName.eq(placeName)
						.and(confirmPlace.reporterId.eq(reporterId)))
				.execute();
	}
}


