package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportReviewDto {
    private String logId;
    private String reporterId;
    private String reportedId;
    private String reportedNickname;
    private String placeName;
    private LocalDateTime reportTime;
    private String review;
    private String reportReason;
}