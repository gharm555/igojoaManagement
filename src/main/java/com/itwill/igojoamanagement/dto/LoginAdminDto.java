package com.itwill.igojoamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginAdminDto {

    private String adminId;
    private String password;
    private String roleName;
}
