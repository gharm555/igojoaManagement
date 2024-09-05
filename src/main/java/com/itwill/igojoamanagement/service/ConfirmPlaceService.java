package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import com.itwill.igojoamanagement.domain.PlaceStats;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;

import com.itwill.igojoamanagement.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmPlaceService {

    private final PlaceImageRepository placeImageRepository;

    private final ConfirmPlaceRepository confirmPlaceRepository;


    private final ConfirmPlaceQueryDsl confirmPlaceQueryDsl;

    private final PlaceRepository placeRepository;

    private final PlaceStatsRepository placeStatsRepository;

    @Transactional(readOnly = true)
    public Page<ConfirmPlaceNameDto> read(Pageable pageable) {
        Page<ConfirmPlace> list = confirmPlaceRepository.findAll(pageable);
        return list.map(ConfirmPlaceNameDto::fromEntity);
    }


    @Transactional
    public ConfirmPlaceDetailsDTO getPlaceDetails(String placeName, String reporterId) {
        log.info("getPlaceDetails - Fetching details for placeName: {}, reporterId: {}", placeName, reporterId);
        return confirmPlaceQueryDsl.findConfirmPlaceDetailsWithImages(placeName, reporterId)
                .orElseThrow(() -> new IllegalArgumentException("ConfirmPlace not found with placeName: " + placeName + " and reporterId: " + reporterId));
    }

    @Transactional
    public void approvePlace(String placeName, String reporterId) {
        log.info("approvePlace - Approving placeName: {}, reporterId: {}", placeName, reporterId);

        if (placeName == null || reporterId == null) {
            throw new IllegalArgumentException("placeName and reporterId must not be null");
        }

        // 중복된 장소 확인
        boolean placeExists = placeRepository.existsByPlaceName(placeName);
        if (placeExists) {
            log.warn("approvePlace - Place already exists with placeName: {}", placeName);
            throw new IllegalArgumentException("이미 승인된 장소입니다.");
        }

        Optional<ConfirmPlaceDetailsDTO> confirmPlaceDetailsDTOOpt =
                confirmPlaceQueryDsl.findConfirmPlaceDetailsWithImages(placeName, reporterId);

        confirmPlaceDetailsDTOOpt.ifPresentOrElse(
                dto -> {
                    log.info("approvePlace - Found ConfirmPlaceDetailsDTO: {}", dto);
                    if (dto.getPlaceLongitude() == null) {
                        throw new IllegalArgumentException("경도는 필수 입력 항목입니다.");
                    }
                    if (dto.getPlaceLatitude() == null) {
                        throw new IllegalArgumentException("위도는 필수 입력 항목입니다.");
                    }
                    // Place 엔티티 생성 및 저장
                    Place place = Place.builder()
                            .placeName(dto.getPlaceName())
                            .largeAddress(dto.getLargeAddress())
                            .midiumAddress(dto.getMediumAddress())
                            .smallAddress(dto.getSmallAddress())
                            .placeDescription(dto.getPlaceDescription())
                            .placeLatitude(dto.getPlaceLatitude())
                            .placeLongitude(dto.getPlaceLongitude())
                            .operatingHours(dto.getOperatingHours())
                            .placeRadius(dto.getRadius())
                            .build();

                    placeRepository.save(place);

                    PlaceStats placeStats = PlaceStats.builder()
                            .placeName(place.getPlaceName())
                            .highestBadge("경치좋음")
                            .secondHighestBadge("야경좋음")
                            .build();
                    placeStatsRepository.save(placeStats);

                    // PlaceImage 엔티티 생성 및 저장
                    PlaceImage placeImage = PlaceImage.builder()
                            .placeName(dto.getPlaceName())
                            .firstUrl(dto.getFirstUrl())
                            .firstImgName(dto.getFirstImageName())
                            .secondUrl(dto.getSecondUrl())
                            .secondImgName(dto.getSecondImageName())
                            .thirdUrl(dto.getThirdUrl())
                            .thirdImgName(dto.getThirdImageName())
                            .build();

                    placeImageRepository.save(placeImage);

                    // ConfirmPlace 삭제
                    confirmPlaceQueryDsl.deleteByPlaceNameAndReporterId(placeName, reporterId);
                },
                () -> {
                    log.error("approvePlace - ConfirmPlace not found with placeName: {} and reporterId: {}", placeName, reporterId);
                    throw new IllegalArgumentException("ConfirmPlace not found with placeName: " + placeName + " and reporterId: " + reporterId);
                }
        );
    }
    @Transactional
    public void ConfirmDelete(String placeName, String reporterId) {

       confirmPlaceQueryDsl.deleteByPlaceNameAndReporterIdAndImg(placeName, reporterId);
    }
}



