package com.itwill.igojoamanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


import com.itwill.igojoamanagement.domain.ConfirmPlace;


public class ConfirmPlaceQueryDslImpl extends QuerydslRepositorySupport implements ConfirmPlaceQueryDsl {

    public ConfirmPlaceQueryDslImpl() {
        super(ConfirmPlace.class);
    }


    @Override
    public Page<ConfirmPlace> findAllWithPaging(Pageable pageable) {

        return null;
    }

    @Override
    public List<ConfirmPlace> findByPlaceNameContaining(String placeName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteByPlaceNameAndReporterId(String placeName, String reporterId) {
        // TODO Auto-generated method stub

    }

}
