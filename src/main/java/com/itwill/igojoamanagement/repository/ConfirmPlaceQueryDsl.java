package com.itwill.igojoamanagement.repository;

import java.util.List;

import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.igojoamanagement.domain.ConfirmPlace;

public interface ConfirmPlaceQueryDsl {

    List<ConfirmPlaceNameDto> findAllConfirmPlaceNames();

}
