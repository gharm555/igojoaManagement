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
public class ConfirmPlaceNameDto {

    private String placeName;

    private String reporterId;

    private String largeAddress;

    private String mediumAddress;

    private String smallAddress;


    public static ConfirmPlaceNameDto fromEntity(ConfirmPlace entity){

        return ConfirmPlaceNameDto.builder()
                .placeName(entity.getPlaceName())
                .reporterId(entity.getReporterId())
                .largeAddress(entity.getLargeAddress())
                .mediumAddress(entity.getMediumAddress())
                .smallAddress(entity.getSmallAddress())
                .build();
    }

}
