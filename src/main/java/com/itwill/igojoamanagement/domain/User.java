package com.itwill.igojoamanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "users")
public class User {

    @Id
    @Column(length = 12, nullable = false)
    @NotBlank(message = "사용자 ID는 필수 입력 항목입니다.")
    private String userId;

    @Column(length = 64, nullable = false)
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 64, message = "비밀번호는 최소 8자에서 최대 64자까지 입력 가능합니다.")
    private String password;

    @Column(length = 64, nullable = false)
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @Column(length = 11, nullable = false)
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "\\d{10,11}", message = "전화번호는 10-11자리의 숫자여야 합니다.")
    private String phoneNumber;

    @Column(length = 10, nullable = false)
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    private String nickName;

    @Column(length = 60)
    private String userProfileName;

    @Column(length = 200)
    private String userProfileUrl;
}
