package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import com.itwill.igojoamanagement.domain.PlaceImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmPlaceDetailsDTO {
    private String placeName;
    private String largeAddress;
    private String mediumAddress;
    private String smallAddress;
    private String placeDescription;
    private double placeLongitude;
    private double placeLatitude;
    private String operatingHours;
    private Integer radius;
    private String firstImageUrl;
    private String secondImageUrl;
    private String thirdImageUrl;

    // Entity에서 DTO로 변환하는 메서드를 추가할 수 있습니다.
    public static ConfirmPlaceDetailsDTO fromEntity(ConfirmPlace confirmPlace, PlaceImage placeImage) {
        return ConfirmPlaceDetailsDTO.builder()
                .placeName(confirmPlace.getPlaceName())
                .largeAddress(confirmPlace.getLargeAddress())
                .mediumAddress(confirmPlace.getMediumAddress())
                .smallAddress(confirmPlace.getSmallAddress())
                .placeDescription(confirmPlace.getPlaceDescription())
                .placeLongitude(confirmPlace.getPlaceLongitude())
                .placeLatitude(confirmPlace.getPlaceLatitude())
                .operatingHours(confirmPlace.getOperatingHours())
                .radius(confirmPlace.getRadius())
                .firstImageUrl(placeImage.getFirstUrl()) // PlaceImage에서 가져온 URL 사용
                .secondImageUrl(placeImage.getSecondUrl()) // PlaceImage에서 가져온 URL 사용
                .thirdImageUrl(placeImage.getThirdUrl()) // PlaceImage에서 가져온 URL 사용
                .build();
    }
}
