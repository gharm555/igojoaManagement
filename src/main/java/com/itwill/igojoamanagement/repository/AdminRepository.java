package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String>{

    Admin findByAdminId(String adminId);
}
