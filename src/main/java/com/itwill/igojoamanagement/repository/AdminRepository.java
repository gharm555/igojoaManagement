package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String>, AdminQueryDsl {
    // 또는 AdminRoles를 직접 조회하고 싶다면 별도의 메서드를 만듭니다
    @Query("SELECT r FROM AdminRoles r WHERE r.roleId = :roleId")
    AdminRoles findByRoleRoleId(@Param("roleId") Integer roleId);
}
