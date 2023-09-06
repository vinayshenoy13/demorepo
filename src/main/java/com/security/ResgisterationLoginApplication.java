package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ResgisterationLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResgisterationLoginApplication.class, args);
	}

}
