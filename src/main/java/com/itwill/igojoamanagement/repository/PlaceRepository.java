package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.Place;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, String>{
    boolean existsByPlaceName(String placeName);
    void deleteByPlaceName(String placeName);
}
