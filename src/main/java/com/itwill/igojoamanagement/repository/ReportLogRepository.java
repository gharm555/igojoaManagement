package com.itwill.igojoamanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.ReportLog;

import java.util.Optional;

public interface ReportLogRepository extends JpaRepository<ReportLog, String> {
    Page<ReportLog> findByReasonCode(Integer reasonCode, Pageable pageable);
    Optional<ReportLog> findFirstByReportedIdOrderByReportTimeDesc(String reportedId);
}
