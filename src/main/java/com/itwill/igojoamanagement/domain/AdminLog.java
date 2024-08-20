package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "adminLogs")
public class AdminLog {

    @Id
    @Column(length = 23, nullable = false)
    @NotBlank(message = "로그 ID는 필수 입력 항목입니다.")
    private String logId;

    @Column(nullable = false)
    @NotNull(message = "생성일시는 필수 입력 항목입니다.") 
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId", nullable = false)
    @NotNull(message = "관리자 ID는 필수 입력 항목입니다.") 
    private Admin admin;

    @Column(name = "category", length = 10, nullable = false)
    @NotBlank(message = "카테고리는 필수 입력 항목입니다.") 
    private String adminLogCategory;

    @Column(length = 300, nullable = false)
    @NotBlank(message = "타겟은 필수 입력 항목입니다.") 
    private String target;

    @Column(length = 10, nullable = false)
    @NotBlank(message = "액션은 필수 입력 항목입니다.") 
    private String action;

    @Column(length = 200)
    private String reason;


}
