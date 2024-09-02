package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.dto.AdminDto;

import java.util.List;
import java.util.Optional;

public interface AdminRepositoryCustom {

    List<AdminDto> findByAdminDtoByAdminId(String adminId);
}
