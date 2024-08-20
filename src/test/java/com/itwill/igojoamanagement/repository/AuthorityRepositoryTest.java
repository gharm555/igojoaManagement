package com.itwill.igojoamanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.igojoamanagement.domain.Authority;
import com.itwill.igojoamanagement.domain.Reason;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;
    	
    @Autowired
    private ReasonRepository reasonRepository;
    
    @Test
    void testDependencyInjection() {
        assertThat(authorityRepository).isNotNull();
        log.debug(authorityRepository.toString());
        assertThat(reasonRepository).isNotNull();
        log.debug(reasonRepository.toString());
    }

   // @Test
    public void testSaveAuthority() {
        // Given
        Authority authority = Authority.builder()
                .adminId("123456789A")
                .authorityCategory("ROLE_USER")
                .authorityCode("USER")
                .build();

        // When
        Authority savedAuthority = authorityRepository.save(authority);

        // Then
        assertNotNull(savedAuthority);
        assertEquals("123456789A", savedAuthority.getAdminId());
        assertEquals("ROLE_USER", savedAuthority.getAuthorityCategory());
        assertEquals("USER", savedAuthority.getAuthorityCode());

        log.info("Authority saved: adminId={}, authorityCategory={}, authorityCode={}",
                savedAuthority.getAdminId(),
                savedAuthority.getAuthorityCategory(),
                savedAuthority.getAuthorityCode());
    }
    @Test
    public void testFindReasonById() {
        // Given
        int reasonCode = 100; 

        // When
        Reason foundReason = reasonRepository.findById(reasonCode).orElse(null);

        // Then
        assertNotNull(foundReason);
        assertEquals(reasonCode, foundReason.getReasonCode());
        log.info("Reason found: reasonCode={}, description={}",
                foundReason.getReasonCode(), foundReason.getDescription());
    }
}