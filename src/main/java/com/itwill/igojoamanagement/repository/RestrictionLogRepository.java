package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.RestrictionLog;
import com.itwill.igojoamanagement.domain.key.RestrictionLogPK;
import com.itwill.igojoamanagement.domain.key.UserBlackListPK;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.UserBlackList;

public interface RestrictionLogRepository extends JpaRepository<RestrictionLog, RestrictionLogPK>{

}
