package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.service.MyUserDetailsService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		To enable apis that perform modification like post,put, delete
		http.csrf(csrf_token -> csrf_token.disable());

//		making all api public
//		http.authorizeHttpRequests(http_request->http_request.anyRequest().permitAll());

//	making all api secure
//		http.authorizeHttpRequests(http_request -> http_request.anyRequest().authenticated())
//		.httpBasic(Customizer.withDefaults());

//		making GET all users api public and all other apis secure
		http.authorizeHttpRequests(http_request -> http_request
//				.requestMatchers(HttpMethod.GET,"users/**").permitAll()
//				.requestMatchers(HttpMethod.GET, "users/**").hasAuthority("HR")
				
//				this is used to display exception status
				.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
//				.requestMatchers(HttpMethod.PUT, "users/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/registerUsers/register").permitAll()
				.requestMatchers(HttpMethod.GET, "products").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.POST, "products").hasAuthority("ADMIN")
//				.requestMatchers(HttpMethod.GET, "products/search/between").hasAuthority("ADMIN")
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
				.authenticationProvider(authenticationProvider());

		return http.build();
	}

//	@Bean
//	public UserDetailsService user() {
//		UserDetails user1 = User.builder().username("vinay").password(encoder().encode("vinay@123")).roles("HR")
//				.build();
//		UserDetails user2 = User.builder().username("shubham").password(encoder().encode("shubham@123"))
//				.roles("TRAINER").build();
//		UserDetails user3 = User.builder().username("nikhil").password(encoder().encode("nikhil@123"))
//				.roles("HR", "ADMIN").build();
//
//		return new InMemoryUserDetailsManager(user1, user2, user3);
//	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.myUserDetailsService);
		provider.setPasswordEncoder(this.encoder());
		return provider;
	}

}
