package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import com.itwill.igojoamanagement.dto.*;
import com.itwill.igojoamanagement.repository.ConfirmPlaceQueryDslImpl;
import com.itwill.igojoamanagement.service.AwsService;
import com.itwill.igojoamanagement.service.ConfirmPlaceService;
import com.itwill.igojoamanagement.service.PlaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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

    private final AwsService awsService;
    private final PlaceService placeService;
    private final ConfirmPlaceService confirmPlaceService;
    private final ConfirmPlaceQueryDslImpl confirmPlaceQueryDslImpl;


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

    @PostMapping("/approvePlace")
    public ResponseEntity<Object> approvePlace(@RequestBody ConfirmPlaceDetailsDTO confirmPlaceDetailsDTO) {
        log.info("Received approval request: {}", confirmPlaceDetailsDTO);
        String placeName = confirmPlaceDetailsDTO.getPlaceName();
        String reporterId = confirmPlaceDetailsDTO.getReporterId();

        try {
            confirmPlaceService.approvePlace(placeName, reporterId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            log.error("Error approving place: {}", e.getMessage());
            if (e.getMessage().contains("이미 승인된 장소입니다")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/placeDelete")
    public ResponseEntity<Object> deletePlace(@RequestBody PlaceDTO placeDTO) {
        String placeName = placeDTO.getPlaceName();


        placeService.deletePlace(placeName);

        return ResponseEntity.ok().body("Place deleted successfully");

    }
    @DeleteMapping("/confirmDelete")
    public ResponseEntity<Object> confirmDeletePlace(@RequestBody ConfirmPlaceNameDto confirmPlaceNameDto) {
        String placeName = confirmPlaceNameDto.getPlaceName();
        String reporterId = confirmPlaceNameDto.getReporterId();

        confirmPlaceService.ConfirmDelete(placeName, reporterId);

        return ResponseEntity.ok().body("ConfirmPlace deleted successfully");

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