package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

// 복합키 클래스
@Embeddable
@Data
public class ReviewPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String placeName;
    private String userId;
}
