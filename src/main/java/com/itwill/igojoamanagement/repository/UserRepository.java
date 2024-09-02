package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.RestrictionLog;
import com.itwill.igojoamanagement.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>{
    Page<User> findAll(Pageable pageable);

    @Query("SELECT r FROM RestrictionLog r WHERE COUNT(r.restrictionLogPK.reportedId) >= 3")
    Page<RestrictionLog> findBlackList(Pageable pageable);
}
