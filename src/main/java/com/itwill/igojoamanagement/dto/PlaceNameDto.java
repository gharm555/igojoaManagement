package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.Place;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceNameDto {
    private String placeName;

    private String largeAddress;

    private String midiumAddress;

    private String smallAddress;

    // 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static PlaceNameDto fromEntity(Place entity) {
        return PlaceNameDto.builder()
                .placeName(entity.getPlaceName())
                .largeAddress(entity.getLargeAddress())
                .midiumAddress(entity.getMidiumAddress())
                .smallAddress(entity.getSmallAddress())
                .build();
    }
}
