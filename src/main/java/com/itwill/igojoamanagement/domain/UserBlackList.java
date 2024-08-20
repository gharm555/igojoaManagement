package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@IdClass(UserBlackListId.class) 
public class UserBlackList {

    @Id
    @Column(length = 12, nullable = false)
    @NotNull(message = "사용자 ID는 필수 입력 항목입니다.")
    private String userId;

    @Id
    @Column(nullable = false)
    @NotNull(message = "차단 시작 날짜는 필수 입력 항목입니다.")
    private LocalDateTime banStartAt;

    @Column(nullable = false)
    @NotNull(message = "사유 코드는 필수 입력 항목입니다.")
    private int reasonCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId", nullable = false)
    @NotNull(message = "관리자 ID는 필수 입력 항목입니다.")
    private Admin admin;
}
