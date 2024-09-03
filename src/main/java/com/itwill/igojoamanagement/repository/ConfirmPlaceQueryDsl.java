package com.itwill.igojoamanagement.repository;


import java.util.Optional;



public interface ConfirmPlaceQueryDsl {

    void deleteByPlaceNameAndReporterId(String placeName, String reporterId);

    Optional<Object> findConfirmPlaceDetailsWithImages(String placeName, String reporterId);
}
