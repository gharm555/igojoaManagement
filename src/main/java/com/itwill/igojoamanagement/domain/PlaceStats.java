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
@Table(name = "placesStats")
public class PlaceStats {

    @Id
    @Column(name = "placeName", length = 20, nullable = false)
    private String placeName;

    @Column(name = "parkingAvailable")
    private Integer parkingAvailable;

    @Column(name = "view")
    private Integer view;

    @Column(name = "nightView")
    private Integer nightView;

    @Column(name = "freeEntry")
    private Integer freeEntry;

    @Column(name = "easyTransport")
    private Integer easyTransport;

    @Column(name = "iScore")
    private Integer iScore;

    @Column(name = "review")
    private Integer review;

    @Column(name = "placeVerified")
    private Integer placeVerified;

    @Column(name = "placeFavorite")
    private Integer placeFavorite;

    @Column(name = "highestBadge")
    private String highestBadge;

    @Column(name = "secondHighestBadge")
    private String secondHighestBadge;


}
