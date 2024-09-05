package com.itwill.igojoamanagement.repository;



import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;

import java.util.Optional;



public interface ConfirmPlaceQueryDsl {

    void deleteByPlaceNameAndReporterId(String placeName, String reporterId);

    void deleteByPlaceNameAndReporterIdAndImg(String placeName, String reporterId);

    Optional<ConfirmPlaceDetailsDTO> findConfirmPlaceDetailsWithImages(String placeName, String reporterId);

}
