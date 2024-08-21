package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.Domain.UserBlackList;
import com.itwill.igojoamanagement.Domain.UserBlackListId;

public interface UserBlackListRepository extends JpaRepository<UserBlackList, UserBlackListId>{

}
