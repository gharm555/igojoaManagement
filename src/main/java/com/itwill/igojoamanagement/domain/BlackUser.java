package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "blackUsers")
public class BlackUser {

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "adminId")
    private String adminId;

    @Column(name = "reasonCode")
    private int reasonCode;

    @Column(name = "detail")
    private String detail;

    @Column(name = "processedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime processedAt;
}