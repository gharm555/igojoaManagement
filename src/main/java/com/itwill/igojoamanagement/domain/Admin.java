package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Entity
@Table(name = "admins")
public class Admin implements UserDetails {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "adminId", length = 10, updatable = false)
    @Pattern(regexp = "\\d{9}[A-Z]", message = "올바른 사번이 아닙니다")
    private String adminId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @Column(name = "birth")
    @NotNull(message = "생년월일은 필수 입력 항목입니다.")
    private String birth;

    @Column(name = "gender")
    @Min(value = 0, message = "성별 코드는 0 이상이어야 합니다.")
    @Max(value = 2, message = "성별 코드는 2 이하여야 합니다.")
    private int gender;

    @Column(name = "email")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Size(max = 64, message = "이메일은 64자를 초과할 수 없습니다.")
    private String email;

    @Column(name = "address")
    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    @Size(max = 100, message = "주소는 100자를 초과할 수 없습니다.")
    private String address;

    @Column(name = "phoneNumber")
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "\\d{10,11}", message = "전화번호는 10-11자리의 숫자여야 합니다.")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private AdminRoles role;

    @Column(name = "hireDate")
    @NotNull(message = "입사일은 필수 입력 항목입니다.")
    private LocalDateTime hireDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return this.adminId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
