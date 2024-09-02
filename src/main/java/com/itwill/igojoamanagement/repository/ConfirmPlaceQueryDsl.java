package com.itwill.igojoamanagement.repository;

import java.util.List;
import java.util.Optional;

import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.igojoamanagement.domain.ConfirmPlace;

public interface ConfirmPlaceQueryDsl {

    List<ConfirmPlaceNameDto> findAllConfirmPlaceNames();

    Optional<ConfirmPlaceDetailsDTO> findPlaceDetailsWithImages(String placeName, String reporterId);


    void deleteByPlaceNameAndReporterId(String placeName, String reporterId);

}
