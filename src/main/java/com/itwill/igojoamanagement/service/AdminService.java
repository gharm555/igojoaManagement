package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import com.itwill.igojoamanagement.dto.LoginAdminDto;
import com.itwill.igojoamanagement.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        LoginAdminDto adminDto = adminRepository.findByAdminIdWithRole(adminId);
        if (adminDto == null) {
            throw new UsernameNotFoundException("User not found with id: " + adminId);
        }
        return new User(adminDto.getAdminId(),
                adminDto.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + adminDto.getAdminGroup() + "_" + adminDto.getAdminRole())));
    }


    @Transactional
    public Admin create(Admin entity) {
        log.info("create {}" , entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        Admin admin = adminRepository.save(entity);
        log.info("admin {}",admin);
        return admin;
    }
}

