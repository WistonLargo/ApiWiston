package com.example.apiwistonspring.mappers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.apiwistonspring.dtos.UserCreateDTO;
import com.example.apiwistonspring.model.entities.UserEntity;

public class UserCreateDTO2UserMapper implements MyMapper<UserCreateDTO, UserEntity> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity map(UserCreateDTO t) {
		String encode = passwordEncoder.encode(t.password());
		return new UserEntity(t.email(), t.username(), encode);
	}
	
	private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d;,\\.]{8,}$");
        return pattern.matcher(password).matches();
    }

}
