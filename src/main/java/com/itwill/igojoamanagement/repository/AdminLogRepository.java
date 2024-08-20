package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.AdminLog;

public interface AdminLogRepository extends JpaRepository<AdminLog, String> {

}
