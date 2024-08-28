package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "adminId", length = 10, updatable = false)
    //    @Pattern(regexp = "\\d{9}[A-Z]", message = "올바른 사번이 아닙니다")
    private String adminId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Column(name = "password")
    private String password;
}
