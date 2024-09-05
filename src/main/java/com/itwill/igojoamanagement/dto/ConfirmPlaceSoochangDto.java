package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.ConfirmPlace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmPlaceSoochangDto {

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
    private String oldFirstUrl;

    public ConfirmPlace toEntity() {
        return ConfirmPlace.builder()
                .placeName(this.placeName)
                .reporterId(this.reporterId)
                .largeAddress(this.largeAddress)
                .mediumAddress(this.mediumAddress)
                .smallAddress(this.smallAddress)
                .placeDescription(this.placeDescription)
                .placeLongitude(this.placeLongitude)
                .placeLatitude(this.placeLatitude)
                .operatingHours(this.operatingHours)
                .radius(this.radius)
                .build();
    }

    public static ConfirmPlaceSoochangDto fromEntity(ConfirmPlace confirmPlace) {
        return ConfirmPlaceSoochangDto.builder()
                .placeName(confirmPlace.getPlaceName())
                .reporterId(confirmPlace.getReporterId())
                .largeAddress(confirmPlace.getLargeAddress())
                .mediumAddress(confirmPlace.getMediumAddress())
                .smallAddress(confirmPlace.getSmallAddress())
                .placeDescription(confirmPlace.getPlaceDescription())
                .placeLongitude(confirmPlace.getPlaceLongitude())
                .placeLatitude(confirmPlace.getPlaceLatitude())
                .operatingHours(confirmPlace.getOperatingHours())
                .radius(confirmPlace.getRadius())
                .build();
    }
}