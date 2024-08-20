package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.ConfirmPlaceId;

public interface ConfirmPlaceReoisitory extends JpaRepository<ConfirmPlace, ConfirmPlaceId>,  ConfirmPlaceQueryDsl {

}
