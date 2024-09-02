package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportedUserDto {
    private String reportedId;
    private String currentNickname;
    private String reportedNickname;
}