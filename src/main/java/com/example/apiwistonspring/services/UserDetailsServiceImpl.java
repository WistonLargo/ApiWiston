package com.example.apiwistonspring.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.model.entities.UserEntity;
import com.example.apiwistonspring.model.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	private UserEntity user;
	private User user2;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("usuario inexistente"));
		Set<SimpleGrantedAuthority> set=user.getRoles().stream()
			.map((rol)->new SimpleGrantedAuthority("ROLE_"+rol.getName().name()))
			.collect(Collectors.toSet());
		System.out.println("UserDetailsServiceImpl: en loadUserByUsername");
		user2 = new User(user.getUsername(), user.getPassword(), true, true, true, true, set);
		return user2;
	}

}
