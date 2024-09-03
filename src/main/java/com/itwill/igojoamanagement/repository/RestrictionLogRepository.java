package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.RestrictionLog;
import com.itwill.igojoamanagement.domain.key.RestrictionLogPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface RestrictionLogRepository extends JpaRepository<RestrictionLog, RestrictionLogPK>{
    @Query("SELECT r FROM RestrictionLog r WHERE COUNT(r.restrictionLogPK.reportedId) >= 3")
    Page<RestrictionLog> findBlackList(Pageable pageable);
}