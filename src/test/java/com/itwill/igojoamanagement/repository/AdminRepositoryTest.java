package com.itwill.igojoamanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminLog;
import com.itwill.igojoamanagement.domain.AdminLoginLog;
import com.itwill.igojoamanagement.domain.AdminRoles;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private AdminLoginLogRepository adminLogRepo;
    
    @Autowired
    private AdminLogRepository adminLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testDependencyInjection() {
        assertThat(adminRepository).isNotNull();
        log.debug(adminRepository.toString());

        assertThat(passwordEncoder).isNotNull();
        log.debug(passwordEncoder.toString());
        
        assertThat(adminLogRepo).isNotNull();
        log.debug(adminLogRepo.toString());
       
        assertThat(adminLogRepository).isNotNull();
        log.debug(adminLogRepository.toString());
    }
   
   // @Test
    public void testSaveAdmin() {
        // Given
        AdminRoles role = AdminRoles.builder()
                .roleId(10)
                .roleName("사원")
                .build();

        Admin admin = Admin.builder()
                .adminId("123456789B")
                .password(passwordEncoder.encode("test1234"))
                .name("진성")
                .birth("1910-01-01")
                .gender(1)
                .email("1234@naver.com")
                .address("서울시 강남구")
                .phoneNumber("01012341234")
                .role(role)
                .hireDate(LocalDateTime.now().toString())
                .build();

        // When
        Admin savedAdmin = adminRepository.save(admin);

    }
    //@Test
    public void testLog() {
    	
        AdminRoles role = AdminRoles.builder()
                .roleId(10)
                .roleName("사원")
                .build();
        Admin admin = Admin.builder()
                .adminId("123456789A")
                .password(passwordEncoder.encode("ralrala10-"))
                .name("요한")
                .birth("1990-01-01")
                .gender(1)
                .email("yohan1235@naver.com")
                .address("서울시 강남구")
                .phoneNumber("01012345678")
                .role(role)
                .hireDate(LocalDateTime.now().toString())
                .build();
        
        AdminLoginLog log = AdminLoginLog.builder()
                .logId("sdfnjksdnkj")
                .admin(admin)
                .loginLogCategory("LOGIN")
                .createdAt(LocalDateTime.now())
                .build();
        assertEquals("sdfnjksdnkj", log.getLogId());
        assertEquals("asdd", log.getAdmin());
        assertEquals("LOGIN", log.getLoginLogCategory());
       

    }

    //@Test
    public void testAdminLogCreation() {
    	LocalDateTime now = LocalDateTime.now();
        AdminRoles role = AdminRoles.builder()
                .roleId(10)
                .roleName("사원")
                .build();
        Admin admin = Admin.builder()
                .adminId("1234")
                .password(passwordEncoder.encode("1234"))
                .name("김진성")
                .birth("1789-01-01")
                .gender(1)
                .email("334234232@naver.com")
                .address("서울시 강남구")
                .phoneNumber("01012345678")
                .role(role)
                .hireDate(LocalDateTime.now().toString())
                .build();

        AdminLog adminLog = AdminLog.builder()
                .logId("log123")
                .createdAt(LocalDateTime.now())
                .admin(admin)
                .adminLogCategory("INFO")
                .target("TargetSystem")
                .action("CREATE")
                .reason("Routine operation")
                .build();
        log.info("AdminLog 객체 생성: logId={}, createdAt={}, adminId={}, category={}, target={}, action={}, reason={}",
                adminLog.getLogId(), 
                adminLog.getCreatedAt(),
                adminLog.getAdmin(),
                adminLog.getAdminLogCategory(),
                adminLog.getTarget(),
                adminLog.getAction(),
                adminLog.getReason());
        
        assertNotNull(adminLog);
        assertEquals("log123", adminLog.getLogId());
        assertEquals(now, adminLog.getCreatedAt());
        assertEquals("admin001", adminLog.getAdmin());
        assertEquals("INFO", adminLog.getAdminLogCategory());
        assertEquals("TargetSystem", adminLog.getTarget());
        assertEquals("CREATE", adminLog.getAction());
        assertEquals("Routine operation", adminLog.getReason());
    }
}


