package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.RegisterUser;
import com.security.service.RegisterUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registerUsers/register")
public class RegisterUserController {

	@Autowired
	RegisterUserService registerUserService;

	@PostMapping("")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUser registerUser) {
		return registerUserService.registerUser(registerUser);
	}

}
