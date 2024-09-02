package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

// 복합키 클래스
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestrictionLogPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reportedId;

    // 관리자에 의해 처리된 시간
    @Column(nullable = false)
    private LocalDateTime restrictionDate;

    public RestrictionLogPK(String userId) {
        this.reportedId = userId;
        this.restrictionDate = LocalDateTime.now();
    }
}