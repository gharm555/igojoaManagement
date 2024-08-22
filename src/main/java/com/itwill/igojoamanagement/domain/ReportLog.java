package com.itwill.igojoamanagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "reportLogs")
public class ReportLog {
    @Id
    @Column(length = 23, nullable = false)
    @NotBlank(message = "로그 ID는 필수 입력 항목입니다.")
    private String logId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "신고자 ID는 필수 입력 항목입니다.")
    private String reporterId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "신고받은 유저 ID는 필수 입력 항목입니다.")
    private String reportedId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "장소 이름은 필수 입력 항목입니다.")
    private String placeName;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp()")
    @NotNull(message = "보고 시간은 필수 입력 항목입니다.")
    private LocalDateTime reportTime;

    private Integer reasonCode;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "보고 이유는 필수 입력 항목입니다.")
    private String reportReason;

    @Column(length = 300, nullable = false)
    @NotBlank(message = "리뷰는 필수 입력 항목입니다.")
    private String review;

    @Column(length = 10, nullable = false, name = "reportedNickName")
    @NotBlank(message = "신고받은 유저의 닉네임은 필수 입력 항목입니다.")
    private String reportedNickname;
}
