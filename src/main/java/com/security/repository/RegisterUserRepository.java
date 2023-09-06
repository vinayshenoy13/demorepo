package com.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.security.model.RegisterUser;

public interface RegisterUserRepository extends JpaRepository<RegisterUser, Integer>{

	
	@RestResource(exported = false)
	<S extends RegisterUser> S save(S entity);
	
	
	@RestResource(exported = false)
	public Optional<RegisterUser> findByPhone(String phone);
	
	@RestResource(exported = false)
	public Optional<RegisterUser> findByUsername(String username);

}
