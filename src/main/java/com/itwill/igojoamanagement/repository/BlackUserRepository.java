package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.BlackUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlackUserRepository extends JpaRepository<BlackUser, String>{
    Page<BlackUser> findAll(Pageable pageable);
    
    @Query("SELECT b FROM BlackUser b WHERE b.confirm = '블랙리스트'")
    Page<BlackUser> findBlackUsers(Pageable pageable);
}
