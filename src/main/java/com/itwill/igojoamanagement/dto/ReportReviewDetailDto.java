package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportReviewDetailDto {

    private String reporterId;
    private String reportReason;
    private String reportedId;
    private String review;

}