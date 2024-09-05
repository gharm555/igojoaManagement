package com.itwill.igojoamanagement.dto;

import lombok.Data;

@Data
public class ChangeReportedNickNameRequest {
    private String logId;
    private String nickName;
    private String reportedId;
}
