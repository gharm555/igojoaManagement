package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.Admin;
import com.itwill.igojoamanagement.domain.AdminRoles;
import com.itwill.igojoamanagement.dto.LoginAdminDto;

public interface AdminQueryDsl {

    LoginAdminDto findByAdminIdWithRole(String adminId);

}
