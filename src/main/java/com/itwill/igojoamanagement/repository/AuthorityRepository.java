package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.Authority;
import com.itwill.igojoamanagement.domain.AuthorityId;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId>{

}
