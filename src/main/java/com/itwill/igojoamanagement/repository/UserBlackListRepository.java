package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.domain.UserBlackList;
import com.itwill.igojoamanagement.domain.UserBlackListId;

public interface UserBlackListRepository extends JpaRepository<UserBlackList, UserBlackListId>{

}
