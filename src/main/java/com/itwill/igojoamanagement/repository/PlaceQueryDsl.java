package com.itwill.igojoamanagement.repository;

import java.util.Optional;

public interface PlaceQueryDsl {

    Optional<Object> findPlaceDetailWithImage(String placeName);
}
