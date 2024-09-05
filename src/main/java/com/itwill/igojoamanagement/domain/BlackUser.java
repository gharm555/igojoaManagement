package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "blackUsers")
public class BlackUser { // 삭제필요할지도? 2024-09-02

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "adminId")
    private String adminId;

    @Column(name = "reasonCode")
    @NotNull(message = "사유 코드는 필수 입력 항목입니다.")
    private int reasonCode;

    @Column(name = "detail")
    private String detail;

    @Column(name = "processedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime processedAt;
    
    @Column(name = "confirm", columnDefinition = "varchar(12) default '블랙리스트'")
    private String confirm;

    public void confirmCancel() {
        this.confirm = "제재철회";
    }

}