package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, String>, AdminQueryDsl {
    @Query("select a from Admin a where a.adminId = :adminId and a.password = :password")
    Admin findByAdminIdAndPassword(String adminId, String password);
}
