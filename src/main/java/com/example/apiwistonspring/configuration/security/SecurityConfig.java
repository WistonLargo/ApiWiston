package com.example.apiwistonspring.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.apiwistonspring.configuration.filters.JwtAuthenticationFilter;
import com.example.apiwistonspring.configuration.filters.JwtAuthorizationFilter;
import com.example.apiwistonspring.configuration.security.jwt.JWTUtils;
import com.example.apiwistonspring.services.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JWTUtils jwtUtils;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JWTUtils jwtUtils,
			JwtAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtUtils = jwtUtils;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		System.out.println("Security Config: Entrando en el security filter chain");
		//la configuracion nueva porque and() esta deprecated
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity
				.csrf(config -> config.disable())
				.authorizeHttpRequests(auth -> {
						auth.requestMatchers("users/hello").permitAll();
						auth.anyRequest().authenticated();})
				.sessionManagement(sess -> {
						sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);})
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		System.out.println("SecurityConfig:terminando configuracion config");
		return defaultSecurityFilterChain;
	}

//	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
//			throws Exception {
//		System.out.println("SecurityConfig:terminando autheticationMAnager");
//		return httpSecurity
//				.getSharedObject(AuthenticationManagerBuilder.class)
//				.userDetailsService(userDetailsServiceImpl)
//				.passwordEncoder(passwordEncoder)
//				.and()
//				.build();
//	}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}

