package com.itwill.igojoamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.igojoamanagement.Domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
