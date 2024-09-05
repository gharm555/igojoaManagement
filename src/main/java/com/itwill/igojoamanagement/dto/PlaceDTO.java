package com.itwill.igojoamanagement.dto;

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


}
