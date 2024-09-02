package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;

public interface AdminQueryDsl {
	Admin findByAdminIdAndPassword(String adminId, String password);
}
