package com.itwill.igojoamanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Test
    void testDependencyInjection() {
        assertThat(adminRepository).isNotNull();
        log.debug(adminRepository.toString());

        assertThat(passwordEncoder).isNotNull();
        log.debug(passwordEncoder.toString());
    }

    @Test
    public void testSaveAdmin() {
        // Given
        AdminRoles role = AdminRoles.builder()
                .roleId(10)
                .roleName("사원")
                .build();

        Admin admin = Admin.builder()
                .adminId("test")
                .password(passwordEncoder.encode("123123123"))
                .name("요한")
                .birth("1990-01-01")
                .gender(1)
                .email("yohan1235@naver.com")
                .address("서울시 강남구")
                .phoneNumber("01012345678")
                .role(role)
                .hireDate(LocalDateTime.now())
                .build();

        // When
        Admin savedAdmin = adminRepository.save(admin);

    }

}
