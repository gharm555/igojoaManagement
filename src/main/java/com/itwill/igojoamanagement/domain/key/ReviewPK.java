package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 복합키 클래스
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String placeName;
    private String userId;
}
