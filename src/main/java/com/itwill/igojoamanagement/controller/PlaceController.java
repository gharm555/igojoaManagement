package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.dto.*;
import com.itwill.igojoamanagement.service.AwsService;
import com.itwill.igojoamanagement.service.ConfirmPlaceService;
import com.itwill.igojoamanagement.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceController {

    private final PlaceService placeService;
    private final ConfirmPlaceService confirmPlaceService;
    private final AwsService awsService;


    @GetMapping("/detailPlacesList")
    public Page<PlaceNameDto> getPlaces(@RequestParam(defaultValue = "0") int page) {
        int size = 10;
        Page<PlaceNameDto> placeNames = placeService.read(PageRequest.of(page, size));
        log.info("placeNames: {}", placeNames);  // 로그 추가
        return placeNames;
    }

    @GetMapping("/detailConfirmList")
    public Page<ConfirmPlaceNameDto> getConfirmPlaces(@RequestParam(defaultValue = "0") int page) {
        int size = 10;
        Page<ConfirmPlaceNameDto> confirmPlaceNames = confirmPlaceService.read(PageRequest.of(page, size));
        log.info("confirmPlaceNames: {}", confirmPlaceNames);  // 로그 추가
        return confirmPlaceNames;
    }

    @GetMapping("/placeDetails/{placeName}")
    public ResponseEntity<Optional<Object>> getPlaceDetail(@PathVariable String placeName) {
        Optional<Object> placeDTO = placeService.getPlaceDetail(placeName);
        return ResponseEntity.ok(placeDTO);
    }

    @GetMapping("/confirmPlaceDetails")
    public ResponseEntity<ConfirmPlaceDetailsDTO> getConfirmPlaceDetails(
            @RequestParam("placeName") String placeName,
            @RequestParam("reporterId") String reporterId) {

        ConfirmPlaceDetailsDTO confirmPlaceDetailsDTO = confirmPlaceService.getPlaceDetails(placeName, reporterId);
        return ResponseEntity.ok(confirmPlaceDetailsDTO);
    }

    @PutMapping("/updateConfirmPlace")
    public ResponseEntity<Long> updateConfirmPlace(
            @ModelAttribute ConfirmPlaceSoochangDto dto,
            @RequestPart(value = "placeImages", required = false) List<MultipartFile> placeImages
            ) {
        log.info("Received PUT request for /api/updateConfirmPlace");
        log.info("Received PlaceConfirmDto: {}", dto);
        log.info("placeImages = {}",placeImages);
        log.info("Received placeImages: {}", placeImages != null ? placeImages.size() : "null");

        String reportUserId = dto.getReporterId();
        // PlaceConfirm 처리
        long res = confirmPlaceService.updatePlace(dto);


        // 이미지 처리
        int res2 = 0;
        if (placeImages != null && !placeImages.isEmpty()) {
            PlaceImageDto placeImageDto = new PlaceImageDto();

            placeImageDto.setPlaceName(dto.getPlaceName());

            List<String> imageNames = new ArrayList<>();
            List<String> imageUrls = new ArrayList<>();

            for (MultipartFile image : placeImages) {
                if (!image.isEmpty()) {
                    String url = awsService.uploadImage(image, reportUserId);
                    imageNames.add(image.getOriginalFilename());
                    imageUrls.add(url);
                }
            }

            placeImageDto.setImageNames(imageNames);
            placeImageDto.setImageUrls(imageUrls);

            confirmPlaceService.insertPlaceImages(placeImageDto);
        }

        return ResponseEntity.ok(res );
    }
}

