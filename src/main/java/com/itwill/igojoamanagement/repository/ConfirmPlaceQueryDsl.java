package com.itwill.igojoamanagement.repository;


import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceSoochangDto;

import java.util.Optional;



public interface ConfirmPlaceQueryDsl {

    void deleteByPlaceNameAndReporterId(String placeName, String reporterId);

    void deleteByPlaceNameAndReporterIdAndImg(String placeName, String reporterId);

    Optional<ConfirmPlaceDetailsDTO> findConfirmPlaceDetailsWithImages(String placeName, String reporterId);

    long updateConfirmPlace(ConfirmPlace dto);

}
