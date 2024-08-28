package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;


    public Admin authenticate(String adminId, String password) {
        log.info("authenticate {} {}", adminId, password);
        Admin admin = adminRepository.findByAdminIdAndPassword(adminId, password);
        if (admin != null && password.equals(admin.getPassword())) {
            return admin;
        } else {
            return null;
        }
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

