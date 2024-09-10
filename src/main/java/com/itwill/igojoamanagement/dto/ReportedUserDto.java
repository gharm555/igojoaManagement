package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReportedUserDto {
    private String logId;
    private String reportedId;
    private String currentNickname;
    private String reportedNickname;
}