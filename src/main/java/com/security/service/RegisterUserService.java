package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.security.model.RegisterUser;
import com.security.repository.RegisterUserRepository;
import com.security.responsewrapper.RegisterUserRR;

@Service
public class RegisterUserService {
	
	@Autowired
	RegisterUserRepository registerUserRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	RegisterUserRR registerUserRR = new RegisterUserRR();
	
	public ResponseEntity<?> registerUser(RegisterUser registerUser)
	{
		boolean isPhoneExist=registerUserRepository.findByPhone(registerUser.getPhone()).isPresent();
		if(isPhoneExist)
		{
			throw new ResponseStatusException(HttpStatus.FOUND,"User with given phone alredy exist");
		}
		
		else if(!registerUser.getPassword().equals(registerUser.getConfPassword()))
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Password Does not matched");

		}
		else
		{
			String password=registerUser.getPassword();
			registerUser.setPassword(passwordEncoder.encode(password));
			RegisterUser saved_user=registerUserRepository.save(registerUser);
			
			registerUserRR.setMessage("User Registred Success");
			registerUserRR.setData(saved_user);
			return new ResponseEntity<>(registerUserRR,HttpStatus.CREATED);	
			
		}
	}

}
