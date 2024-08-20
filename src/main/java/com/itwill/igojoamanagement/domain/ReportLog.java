package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "reportLogs")
public class ReportLog {

    @Id
    @Column(length = 23, nullable = false)
    @NotBlank(message = "로그 ID는 필수 입력 항목입니다.")
    private String logId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "보고자 ID는 필수 입력 항목입니다.")
    private String reporterId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "피보고자 ID는 필수 입력 항목입니다.")
    private String reportedId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "장소 이름은 필수 입력 항목입니다.")
    private String placeName;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp()")
    @NotNull(message = "보고 시간은 필수 입력 항목입니다.")
    private LocalDateTime reportTime;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "보고 이유는 필수 입력 항목입니다.")
    private String reportReason;

    @Column(length = 300, nullable = false)
    @NotBlank(message = "리뷰는 필수 입력 항목입니다.")
    private String review;

    @Column(length = 10, nullable = false)
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    private String nickName;
}
