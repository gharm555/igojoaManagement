package com.itwill.igojoamanagement.repository;

import com.itwill.igojoamanagement.domain.key.UserBlackListPK;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.UserBlackList;

public interface UserBlackListRepository extends JpaRepository<UserBlackList, UserBlackListPK>{

}
