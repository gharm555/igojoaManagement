package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String>, AdminRepositoryCustom{

    Optional<Admin> findByAdminId(String adminId);
}
