package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;

public interface AdminQueryDsl {

    Admin findByAdminIdWithRole(String adminId);
}
