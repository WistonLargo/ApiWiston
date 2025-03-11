package com.example.apiwistonspring.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.apiwistonspring.configuration.security.jwt.JWTUtils;
import com.example.apiwistonspring.dtos.UserCreateDTO;
import com.example.apiwistonspring.mappers.UserCreateDTO2UserMapper;
import com.example.apiwistonspring.model.entities.ERole;
import com.example.apiwistonspring.model.entities.RoleUser;
import com.example.apiwistonspring.model.entities.UserEntity;
import com.example.apiwistonspring.model.repositories.RoleRepository;
import com.example.apiwistonspring.model.repositories.UserRepository;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class UserService {
	
	private  UserRepository userRepository;
	private  RoleRepository roleRepository;
	private  UserCreateDTO2UserMapper userCreateDTO2UserMapper;
	private  JWTUtils jwtUtils;
	private  BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			UserCreateDTO2UserMapper userCreateDTO2UserMapper,JWTUtils jwtUtils,BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userCreateDTO2UserMapper = userCreateDTO2UserMapper;
		this.jwtUtils=jwtUtils;
		this.passwordEncoder = passwordEncoder;
		
	}
	public void refreshingToken(HttpServletResponse response, String authorizationHeader)
			throws IOException, StreamWriteException, DatabindException {
		try {
			
			String refresh_token = authorizationHeader.substring("Bearer ".length());
			//Aqui vamos a obtener la clave secreta con el algoritmo aplicado
			Algorithm algorithm = Algorithm.HMAC384(jwtUtils.getSignatureKey().getEncoded());
			// necesita la dependencia auth0
			//Aqui tenemos al verificador que va a usar este algoritmo
			JWTVerifier verifier = JWT.require(algorithm).build();
			//el token se decodificara si pasa la verificacion del algoritmo
			//debe ser 384 porque es el interno de JWT
			DecodedJWT decodedJWT = verifier.verify(refresh_token);
			String username = decodedJWT.getSubject();
			String access_token = jwtUtils.generateAccessToken(username);
			HashMap<String, String> tokens = new HashMap<>();
			tokens.put("access_token", access_token);
			//puedo no entregar el accessToken porque no va a cambiar
//			tokens.put("refresh_token", refresh_token);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		} catch (Exception e) {
			response.setHeader("error", e.getMessage());
			response.setStatus(FORBIDDEN.value());
			HashMap<String, String> error = new HashMap<>();
			error.put("error_message", e.getMessage());
			response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
			try {
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			} catch (StreamWriteException e1) {
				e1.printStackTrace();
			} catch (DatabindException e1) {
				e1.printStackTrace();
			} catch (java.io.IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// igual es momento de usar Response
	public boolean createUser(UserCreateDTO userCreateDTO) {
		UserEntity map = userCreateDTO2UserMapper.map(userCreateDTO);
		if (userCreateDTO.roles() != null) {
			Set<RoleUser> roles = fillRoles(userCreateDTO.roles());
			map.setRoles(roles);
		}
		if (userRepository.save(map) != null) {
			return true;
		}
		return false;
	}

	private Set<RoleUser> fillRoles(String[] t) {
		if (ERole.validate(t)) {
			return Arrays.asList(t).stream()
					.map((rol) -> {
							ERole rolresult = ERole.getRole(rol).get();
							Optional<RoleUser> byName = roleRepository.findByName(rolresult);
							RoleUser roleEntity = byName.get();
							return roleEntity;})
					.collect(Collectors.toSet());
		}
		return null;
	}

	public boolean delete(String username) {
		Optional<UserEntity> byUsername = userRepository.findByUsername(username);
		userRepository.delete(byUsername.get());
		return true;
	}
	public boolean authenticateUser(String username, String rawPassword) {
        // Buscar usuario por nombre de usuario
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar la contraseña utilizando BCrypt
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
