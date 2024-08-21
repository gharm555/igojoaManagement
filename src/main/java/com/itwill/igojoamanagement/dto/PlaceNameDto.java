package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.Place;
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

    // 엔티티를 DTO로 변환하는 정적 팩토리 메서드
    public static PlaceNameDto fromEntity(Place entity) {
        return PlaceNameDto.builder()
                .placeName(entity.getPlaceName())
                .build();
    }
}
