package com.itwill.igojoamanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String email;
    private String phoneNumber;
    private String nickName;

    // For TeamLeader
    public UserDto(String userId, String email, String phoneNumber, String nickName) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
    }

    // For TeamMember
    public UserDto(String userId, String nickName) {
        this.userId = userId;
        this.nickName = nickName;
    }

    public UserDto(String userId) {
        this.userId = userId;
    }

}
