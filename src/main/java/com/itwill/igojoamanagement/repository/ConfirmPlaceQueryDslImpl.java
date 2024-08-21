package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.QConfirmPlace;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


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
}


