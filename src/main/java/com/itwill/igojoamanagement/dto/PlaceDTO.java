package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.domain.PlaceImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDTO {
    private String placeName;
    private String largeAddress;
    private String mediumAddress;
    private String smallAddress;
    private String placeDescription;
    private Double placeLatitude;
    private Double placeLongitude;
    private String operatingHours;
    private Integer placeRadius;
    private String firstUrl;
    private String firstImageName;
    private String secondUrl;
    private String secondImageName;
    private String thirdUrl;
    private String thirdImageName;

    // Entity에서 DTO로 변환하는 메서드 추가
    public static PlaceDTO fromEntity(Place place, PlaceImage placeImage) {
        return PlaceDTO.builder()
                .placeName(place.getPlaceName())
                .largeAddress(place.getLargeAddress())
                .mediumAddress(place.getMidiumAddress()) // 'medium' 오타 수정 필요
                .smallAddress(place.getSmallAddress())
                .placeDescription(place.getPlaceDescription())
                .placeLatitude(place.getPlaceLatitude())
                .placeLongitude(place.getPlaceLongitude())
                .operatingHours(place.getOperatingHours())
                .placeRadius(place.getPlaceRadius())
                .firstUrl(placeImage != null ? placeImage.getFirstUrl() : null) // Null check for placeImage
                .firstImageName(placeImage.getFirstImgName())
                .secondUrl(placeImage != null ? placeImage.getSecondUrl() : null)
                .secondImageName(placeImage.getSecondImgName())
                .thirdUrl(placeImage != null ? placeImage.getThirdUrl() : null)
                .thirdImageName(placeImage.getThirdImgName())
                .build();
    }
}
