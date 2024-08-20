package com.itwill.igojoamanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.igojoamanagement.domain.ConfirmPlace;

public interface ConfirmPlaceQueryDsl {

	
    Page<ConfirmPlace> findAllWithPaging(Pageable pageable);
    List<ConfirmPlace> findByPlaceNameContaining(String placeName);
    void deleteByPlaceNameAndReporterId(String placeName, String reporterId);
}
