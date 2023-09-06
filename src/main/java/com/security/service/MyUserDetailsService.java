package com.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.security.model.RegisterUser;
import com.security.repository.RegisterUserRepository;

import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private RegisterUserRepository registerUserRepository;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RegisterUser userFound =registerUserRepository.findByUsername(username).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Username does not exist");
		});
		
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		String userName= userFound.getUsername();
		String userPassword= userFound.getPassword();
		List<String> userRoles= userFound.getRoles();
		
		for(String role:userRoles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		RegisterUser registerUser= new RegisterUser(userName,userPassword,authorities);
		return registerUser;
	}

	
}
