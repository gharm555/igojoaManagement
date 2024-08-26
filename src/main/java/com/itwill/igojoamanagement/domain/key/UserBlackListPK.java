package com.itwill.igojoamanagement.domain.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

// 복합키 클래스
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBlackListPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    // 관리자에 의해 처리된 시간
    @Column(nullable = false)
    private LocalDateTime processedAt;

    public UserBlackListPK(String userId) {
        this.userId = userId;
        this.processedAt = LocalDateTime.now();
    }
}
