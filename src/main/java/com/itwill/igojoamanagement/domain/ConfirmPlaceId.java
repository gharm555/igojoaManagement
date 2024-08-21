package com.itwill.igojoamanagement.domain;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ConfirmPlaceId implements Serializable {

    private String placeName;
    private String reporterId;

  

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmPlaceId that = (ConfirmPlaceId) o;
        return Objects.equals(placeName, that.placeName) && Objects.equals(reporterId, that.reporterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName, reporterId);
    }
}
