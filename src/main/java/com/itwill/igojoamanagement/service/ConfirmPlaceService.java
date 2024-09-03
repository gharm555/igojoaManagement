package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;

import com.itwill.igojoamanagement.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmPlaceService {

    @Autowired
    private PlaceImageRepository placeImageRepository;
    @Autowired
    private ConfirmPlaceRepoisitory confirmPlaceRepoisitory;

    private ConfirmPlaceQueryDsl confirmPlaceQueryDsl;


    @Autowired
    private PlaceRepository placeRepository;

//    @Autowired
//    private S3Service s3Service;

    @Transactional(readOnly = true)
    public Page<ConfirmPlaceNameDto> read(Pageable pageable) {
        Page<ConfirmPlace> list = confirmPlaceRepoisitory.findAll(pageable);
        return list.map(ConfirmPlaceNameDto::fromEntity);
    }



    @Transactional
    public ConfirmPlaceDetailsDTO getPlaceDetails(String placeName, String reporterId){
        log.info("Fetching details for placeName: {}, reporterId: {}", placeName, reporterId);
        return (ConfirmPlaceDetailsDTO) confirmPlaceRepoisitory.findConfirmPlaceDetailsWithImages(placeName, reporterId).orElse(null);
    }


//    @Transactional
//    public void approveConfirmPlace(String placeName, String reporterId) {
//        // ConfirmPlace 데이터 조회
//        ConfirmPlaceDetailsDTO confirmPlaceDetails = (ConfirmPlaceDetailsDTO) confirmPlaceRepoisitory.findConfirmPlaceDetailsWithImages(placeName, reporterId)
//                .orElseThrow(() -> new IllegalArgumentException("Place not found for approval"));
//
//        // Place 객체 생성 및 저장
//        Place place = Place.builder()
//                .placeName(confirmPlaceDetails.getPlaceName())
//                .largeAddress(confirmPlaceDetails.getLargeAddress())
//                .midiumAddress(confirmPlaceDetails.getMediumAddress())
//                .smallAddress(confirmPlaceDetails.getSmallAddress())
//                .placeDescription(confirmPlaceDetails.getPlaceDescription())
//                .placeLongitude(confirmPlaceDetails.getPlaceLongitude())
//                .placeLatitude(confirmPlaceDetails.getPlaceLatitude())
//                .operatingHours(confirmPlaceDetails.getOperatingHours())
//                .placeRadius(confirmPlaceDetails.getRadius())
//                .build();
//
//        Place savedPlace = placeRepository.save(place);
//
//         PlaceImage 객체 생성 및 저장
//        PlaceImage placeImage = PlaceImage.builder()
//                .placeName(savedPlace.getPlaceName())
//                .firstImgName(confirmPlaceDetails.getFirstUrl())
//                .firstUrl(confirmPlaceDetails.getFirstUrl())
//                .secondImgName(confirmPlaceDetails.getSecondImageUrl())
//                .secondUrl(confirmPlaceDetails.getSecondImageUrl())
//                .thirdImgName(confirmPlaceDetails.getThirdImageUrl())
//                .thirdUrl(confirmPlaceDetails.getThirdImageUrl())
//                .build();
//
//        placeImageRepository.save(placeImage);
//
//         ConfirmPlace 삭제
//        confirmPlaceRepoisitory.deleteByPlaceNameAndReporterId(placeName, reporterId);
//
//        log.info("Approved ConfirmPlace: {} and moved to Place with name: {}", placeName, savedPlace.getPlaceName());
//    }



}
