package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import com.itwill.igojoamanagement.domain.PlaceStats;
import com.itwill.igojoamanagement.dto.PlaceNameDto;
import com.itwill.igojoamanagement.repository.PlaceImageRepository;
import com.itwill.igojoamanagement.repository.PlaceQueryDsl;
import com.itwill.igojoamanagement.repository.PlaceRepository;
import com.itwill.igojoamanagement.repository.PlaceStatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    @Autowired
    private  PlaceRepository placeRepo;
    @Autowired
    private PlaceImageRepository placeImageRepo;
    @Autowired
    private PlaceStatsRepository placeStatsRepository;

    @Autowired
    private PlaceQueryDsl placeQueryDsl;

    @Transactional(readOnly = true)
    public Page<PlaceNameDto> read(Pageable pageable) {
        Page<Place> placePage = placeRepo.findAll(pageable); // 페이징 처리된 Place 엔티티 조회
        log.info("Place 엔티티 개수: {}", placePage.getTotalElements());

        // Place 엔티티를 PlaceNameDto로 변환하여 페이징 처리된 결과를 반환
        Page<PlaceNameDto> placeNameDtoPage = placePage.map(PlaceNameDto::fromEntity);

        log.info("PlaceNameDto 변환 후 개수: {}", placeNameDtoPage.getTotalElements());
        return placeNameDtoPage;
    }

    @Transactional(readOnly = true)
    public Optional<Object> getPlaceDetail(String placeName) {
        return placeQueryDsl.findPlaceDetailWithImage(placeName);
    }
    @Transactional
    public void deletePlace(String placeName) {
        placeImageRepo.deleteByPlaceName(placeName);
        placeRepo.deleteByPlaceName(placeName);
        placeStatsRepository.deleteById(placeName);

    }

}
