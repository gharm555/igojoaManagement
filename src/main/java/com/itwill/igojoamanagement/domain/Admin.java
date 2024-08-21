package com.itwill.igojoamanagement.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Entity
@Table(name = "admins")
public class Admin implements UserDetails {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "adminId", length = 10, updatable = false)
    //    @Pattern(regexp = "\\d{9}[A-Z]", message = "올바른 사번이 아닙니다")
    private String adminId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Column(name = "password")
    private String password;

    @JoinColumn(name = "adminGroup")
    private String adminGroup;

    @JoinColumn(name="adminRole")
    private String adminRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getAdminGroup() + "_" + this.getAdminRole()));
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
