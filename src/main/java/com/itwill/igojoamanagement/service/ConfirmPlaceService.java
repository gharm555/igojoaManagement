package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;

import com.itwill.igojoamanagement.dto.ConfirmPlaceSoochangDto;
import com.itwill.igojoamanagement.dto.PlaceImageDto;
import com.itwill.igojoamanagement.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmPlaceService {

    @Autowired
    private PlaceImageRepository placeImageRepository;

    @Autowired
    private ConfirmPlaceRepoisitory confirmPlaceRepoisitory;

    @Autowired
    private ConfirmPlaceQueryDsl confirmPlaceQueryDsl;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AwsService awsService;



    @Transactional(readOnly = true)
    public Page<ConfirmPlaceNameDto> read(Pageable pageable) {
        Page<ConfirmPlace> list = confirmPlaceRepoisitory.findAll(pageable);
        return list.map(ConfirmPlaceNameDto::fromEntity);
    }


    @Transactional
    public ConfirmPlaceDetailsDTO getPlaceDetails(String placeName, String reporterId) {
        log.info("getPlaceDetails - Fetching details for placeName: {}, reporterId: {}", placeName, reporterId);
        return confirmPlaceQueryDsl.findConfirmPlaceDetailsWithImages(placeName, reporterId)
                .orElseThrow(() -> new IllegalArgumentException("ConfirmPlace not found with placeName: " + placeName + " and reporterId: " + reporterId));
    }


    @Transactional
    public void approveConfirmPlace(String placeName, String reporterId) {
        // ConfirmPlace 데이터 조회
        ConfirmPlaceDetailsDTO confirmPlaceDetails = (ConfirmPlaceDetailsDTO) confirmPlaceQueryDsl.findConfirmPlaceDetailsWithImages(placeName, reporterId)
                .orElseThrow(() -> new IllegalArgumentException("Place not found for approval"));

        // Place 객체 생성 및 저장
        Place place = Place.builder()
                .placeName(confirmPlaceDetails.getPlaceName())
                .largeAddress(confirmPlaceDetails.getLargeAddress())
                .midiumAddress(confirmPlaceDetails.getMediumAddress())
                .smallAddress(confirmPlaceDetails.getSmallAddress())
                .placeDescription(confirmPlaceDetails.getPlaceDescription())
                .placeLongitude(confirmPlaceDetails.getPlaceLongitude())
                .placeLatitude(confirmPlaceDetails.getPlaceLatitude())
                .operatingHours(confirmPlaceDetails.getOperatingHours())
                .placeRadius(confirmPlaceDetails.getRadius())
                .build();

        Place savedPlace = placeRepository.save(place);


        PlaceImage placeImage = PlaceImage.builder()
                .placeName(savedPlace.getPlaceName())
                .firstImgName(confirmPlaceDetails.getFirstUrl())
                .firstUrl(confirmPlaceDetails.getFirstUrl())
                .secondImgName(confirmPlaceDetails.getSecondUrl())
                .secondUrl(confirmPlaceDetails.getSecondUrl())
                .thirdImgName(confirmPlaceDetails.getThirdUrl())
                .thirdUrl(confirmPlaceDetails.getThirdUrl())
                .build();

        placeImageRepository.save(placeImage);


        confirmPlaceQueryDsl.deleteByPlaceNameAndReporterId(placeName, reporterId);

        log.info("Approved ConfirmPlace: {} and moved to Place with name: {}", placeName, savedPlace.getPlaceName());
    }


    // ---------- 수창작업
    @Transactional
    public long updatePlace(ConfirmPlaceSoochangDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ConfirmPlaceDetailsDTO cannot be null");
        }
            ConfirmPlace confirmPlace = dto.toEntity();
            return confirmPlaceQueryDsl.updateConfirmPlace(confirmPlace);

    }



@Transactional
public void insertPlaceImages(PlaceImageDto placeImageDto) {
    int result = 0;
    String placeName = placeImageDto.getPlaceName();
    List<String> imageNames = placeImageDto.getImageNames();
    List<String> imageUrls = placeImageDto.getImageUrls();
    PlaceImage image = new PlaceImage();
    image.setPlaceName(placeName);



    PlaceImage placeImages = new PlaceImage();
    placeImages.setPlaceName(placeName);

    String[] imageNameArray = new String[3];
    String[] imageUrlArray = new String[3];

    // 이미지 이름 처리
    for (int i = 0; i < 3; i++) {
        if (i < imageNames.size()) {
            imageNameArray[i] = imageNames.get(i);
        } else {
            imageNameArray[i] = null;
        }
    }

    // 이미지 URL 처리
    for (int i = 0; i < 3; i++) {
        if (i < imageUrls.size()) {
            imageUrlArray[i] = imageUrls.get(i);
        } else {
            imageUrlArray[i] = null;
        }
    }

    // PlaceImages 객체에 값 설정
    placeImages.setFirstImgName(imageNameArray[0]);
    placeImages.setFirstUrl(imageUrlArray[0]);
    placeImages.setSecondImgName(imageNameArray[1]);
    placeImages.setSecondUrl(imageUrlArray[1]);
    placeImages.setThirdImgName(imageNameArray[2]);
    placeImages.setThirdUrl(imageUrlArray[2]);


    placeImageRepository.save(placeImages);
}



        // ---------- 수창 작업 끝
}




