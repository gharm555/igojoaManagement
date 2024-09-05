package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAdminId(adminId);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found with id: " + adminId);
        }
        return new User(admin.getAdminId(),
                admin.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + admin.getAdminGroup() + "_" + admin.getAdminRole())));
    }


    @Transactional
    public Admin create(Admin entity) {
        log.info("create {}", entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        Admin admin = adminRepository.save(entity);
        log.info("admin {}", admin);
        return admin;
    }

}



