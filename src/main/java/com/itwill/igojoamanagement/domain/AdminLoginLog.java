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
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "adminLoginLogs")
public class AdminLoginLog {

    @Id
    @Column(length = 23, nullable = false)
    @NotBlank(message = "로그 ID는 필수 입력 항목입니다.")
    @Size(max = 23, message = "로그 ID는 23자 이내로 입력해야 합니다.")
    private String logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId", nullable = false)
    @NotNull(message = "관리자 ID는 필수 입력 항목입니다.")
    private Admin admin;

    @Column(name = "category", length = 10, nullable = false)
    @NotBlank(message = "카테고리는 필수 입력 항목입니다.")
    @Size(max = 10, message = "카테고리는 10자 이내로 입력해야 합니다.")
    private String loginLogCategory;

    @Column(nullable = false)
    @NotNull(message = "생성일시는 필수 입력 항목입니다.")
    private LocalDateTime createdAt;


}
