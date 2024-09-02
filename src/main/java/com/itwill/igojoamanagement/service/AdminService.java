package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.dto.AdminDto;
import com.itwill.igojoamanagement.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public Admin signIn(String adminId, String password) {
        log.info("adminId: {}", adminId);
        Admin admin = adminRepository.findByAdminId(adminId).orElseThrow();

        if (!password.equals(admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return admin;
    }

    @Transactional
    public List<AdminDto> getAdminAuthority(String adminId) {
        log.info("adminId: {}", adminId);
        List<AdminDto> result = adminRepository.findByAdminDtoByAdminId(adminId);
        log.info("Admin Authorities for admin ID {}: ", adminId);
        for (AdminDto authority : result) {
            log.info("Authority: {}", authority);
        }
        return result;
    }

    @Transactional
    public Admin create(Admin entity) {
        log.info("create {}", entity);
        entity.setPassword(entity.getPassword());
        Admin admin = adminRepository.save(entity);
        log.info("admin {}", admin);
        return admin;
    }
}

