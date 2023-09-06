package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.User;

@Repository
public interface UserRespository  extends JpaRepository<User,Integer>{

}
