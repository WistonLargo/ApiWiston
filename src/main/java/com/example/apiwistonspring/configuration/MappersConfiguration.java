package com.example.apiwistonspring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.apiwistonspring.mappers.UserCreateDTO2UserMapper;

@Configuration
public class MappersConfiguration {
	
	@Bean
	public UserCreateDTO2UserMapper getCreateDTO2UserMapper() {
		return new UserCreateDTO2UserMapper();
	}

}
