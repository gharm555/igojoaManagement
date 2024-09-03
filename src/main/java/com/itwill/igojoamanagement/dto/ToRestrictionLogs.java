package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToRestrictionLogs {
    private String logId;
    private String reportedId;
    private String adminId;
    private Integer reasonCode;
    private String detail;
}
