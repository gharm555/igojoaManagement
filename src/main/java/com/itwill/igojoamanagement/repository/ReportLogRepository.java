package com.itwill.igojoamanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.ReportLog;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReportLogRepository extends JpaRepository<ReportLog, String> {
   
    Page<ReportLog> findByReasonCode(Integer reasonCode, Pageable pageable);
   
    Optional<ReportLog> findFirstByReportedIdOrderByReportTimeDesc(String reportedId);

    @Query("SELECT r FROM ReportLog r WHERE r.reasonCode = 102 AND r.confirm = '대기중'")
    Page<ReportLog> findReportedUser(Pageable pageable);

}
