package com.example.apiwistonspring.configuration.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.apiwistonspring.configuration.security.jwt.JWTUtils;
import com.example.apiwistonspring.model.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//vamos a utilizar una autentication con username y password
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final JWTUtils jwtUtils;

	public JwtAuthenticationFilter(JWTUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserEntity userEntity;
		String username;
		String password;
		try {
			// En el request viene el objeto en el body que se puede mapear a user
			userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
			username = userEntity.getUsername();
			password = userEntity.getPassword();
			// aqui consigo el token que envio al user para autenticacion
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			// aqui retorno la autenticacion
			Authentication authenticate = getAuthenticationManager().authenticate(authenticationToken);
			System.out.println("JwtAuthenticationFilter:terminado intento authenticate");
			return authenticate;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		System.out.println("JwtAuthenticationFilter:fallo autenticado");
		
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// esta clase user se importa de otro sitio?
		// hemos de pillar el objeto autenticado
		User user = (User) authResult.getPrincipal();
		// hacemos un token de acceso con el username
		String accessToken = jwtUtils.generateAccessToken(user.getUsername());
		String refreshToken = jwtUtils.generateRefreshToken(user);
		// escribimos el token en la cabecera
		response.addHeader("Authorization", accessToken);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("accesstoken", accessToken);
		hashMap.put("refreshToken", refreshToken);
		hashMap.put("Message", "autenticacion correcta");
		hashMap.put("usario", user.getUsername());

		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		PrintWriter ss;
		response.getWriter().write(new ObjectMapper().writeValueAsString(hashMap));
		response.getWriter().flush();

		super.successfulAuthentication(request, response, chain, authResult);
		System.out.println("JwtAuthenticationFilter:usuario autenticado");
	}

}

