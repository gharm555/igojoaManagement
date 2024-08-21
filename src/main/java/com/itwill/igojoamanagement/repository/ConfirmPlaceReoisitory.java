package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.Domain.ConfirmPlace;
import com.itwill.igojoamanagement.Domain.ConfirmPlaceId;

public interface ConfirmPlaceReoisitory extends JpaRepository<ConfirmPlace, ConfirmPlaceId>,  ConfirmPlaceQueryDsl {

}
