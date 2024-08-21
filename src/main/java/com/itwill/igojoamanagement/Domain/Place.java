package com.itwill.igojoamanagement.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "places")
public class Place {

    @Id
    @Column(length = 20, nullable = false)
    @NotBlank(message = "장소 이름은 필수 입력 항목입니다.")
    private String placeName;

    @Column(length = 10)
    private String largeAddress;

    @Column(length = 10)
    private String midiumAddress;

    @Column(length = 30)
    private String smallAddress;

    @Column(length = 400)
    private String placeDescription;

    @Column(nullable = false)
    @NotNull(message = "위도는 필수 입력 항목입니다.")
    private double placeLatitude;

    @Column(nullable = false)
    @NotNull(message = "경도는 필수 입력 항목입니다.")
    private double placeLongitude;

    @Column(length = 500)
    private String operatingHours;

    @Column
    private Integer placeRadius;
}
