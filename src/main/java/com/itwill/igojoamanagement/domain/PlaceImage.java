package com.itwill.igojoamanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "placeImages")
public class PlaceImage {

    @Id
    @Column(name = "placeName", length = 20, nullable = false)
    private String placeName;

    @Column(name = "firstUrl", length = 200, nullable = false)
    private String firstUrl;

    @Column(name = "firstImgName", length = 300)
    private String firstImgName;

    @Column(name = "secondImgName", length = 300)
    private String secondImgName;

    @Column(name = "secondUrl", length = 200)
    private String secondUrl;

    @Column(name = "thirdImgName", length = 300)
    private String thirdImgName;

    @Column(name = "thirdUrl", length = 200)
    private String thirdUrl;
}