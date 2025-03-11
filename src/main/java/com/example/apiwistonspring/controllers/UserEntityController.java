package com.example.apiwistonspring.controllers;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiwistonspring.dtos.UserCreateDTO;
import com.example.apiwistonspring.model.entities.UserEntity;
import com.example.apiwistonspring.services.UserService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@EnableWebSecurity
public class UserEntityController {
	private final UserService userService;
	
	public UserEntityController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/put")
	public ResponseEntity<Boolean> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		if (userService.createUser(userCreateDTO)) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.badRequest().body(false);
	}

	@DeleteMapping("/delet")
	public ResponseEntity<Boolean> delete(@RequestParam String username){
		return ResponseEntity.ok(userService.delete(username));
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				userService.refreshingToken(response, authorizationHeader);
			} catch (StreamWriteException e) {
				e.printStackTrace();
			} catch (DatabindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			  throw new RuntimeException("Refresh token is missing");
		}
	}
}

