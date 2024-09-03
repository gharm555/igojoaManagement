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
    private String reporterId;
    private String largeAddress;
    private String mediumAddress;
    private String smallAddress;
    private String placeDescription;
    private Double placeLongitude;
    private Double placeLatitude;
    private String operatingHours;
    private Integer radius;
    private String firstUrl;
    private String firstImageName;
    private String secondUrl;
    private String secondImageName;
    private String thirdUrl;
    private String thirdImageName;

    // Entity에서 DTO로 변환하는 메서드를 추가할 수 있습니다.
    public static ConfirmPlaceDetailsDTO fromEntity(ConfirmPlace confirmPlace, PlaceImage placeImage) {
        return ConfirmPlaceDetailsDTO.builder()
                .placeName(confirmPlace.getPlaceName())
                .placeName(confirmPlace.getReporterId())
                .largeAddress(confirmPlace.getLargeAddress())
                .mediumAddress(confirmPlace.getMediumAddress())
                .smallAddress(confirmPlace.getSmallAddress())
                .placeDescription(confirmPlace.getPlaceDescription())
                .placeLongitude(confirmPlace.getPlaceLongitude())
                .placeLatitude(confirmPlace.getPlaceLatitude())
                .operatingHours(confirmPlace.getOperatingHours())
                .radius(confirmPlace.getRadius())
                .firstUrl(placeImage.getFirstUrl()) // PlaceImage에서 가져온 URL 사용
                .firstImageName(placeImage.getFirstImgName())
                .secondUrl(placeImage.getSecondUrl()) // PlaceImage에서 가져온 URL 사용
                .secondImageName(placeImage.getSecondImgName())
                .thirdUrl(placeImage.getThirdUrl()) // PlaceImage에서 가져온 URL 사용
                .thirdImageName(placeImage.getThirdImgName())
                .build();
    }
}
