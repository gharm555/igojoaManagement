package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import com.itwill.igojoamanagement.dto.ConfirmPlaceDetailsDTO;
import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import com.itwill.igojoamanagement.dto.PlaceDTO;
import com.itwill.igojoamanagement.dto.PlaceNameDto;
import com.itwill.igojoamanagement.repository.ConfirmPlaceQueryDslImpl;
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

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceController {

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
}


