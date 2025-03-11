package com.example.apiwistonspring.configuration.filters;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.apiwistonspring.configuration.security.jwt.JWTUtils;
import com.example.apiwistonspring.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//hay que identificarse con el token una vez por peticion a un endpoint
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JWTUtils jwtUtils;

	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public JwtAuthorizationFilter(JWTUtils jwtUtils, UserDetailsServiceImpl userDetailsServiceImpl) {
		super();
		this.jwtUtils = jwtUtils;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		System.out.println("JwtAuthorizationFilter: entrando en el filtro");
		String header = request.getHeader("Authorization");
		System.out.println("JwtAuthorizationFilter: el header "+header);
		isValidBearerHeader(header).ifPresent((head -> {
			if (jwtUtils.isTokenValid(head)) {
				String username = jwtUtils.getUSerNameFromToken(head);
				UserDetails userByUsername = userDetailsServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
						userByUsername.getAuthorities());
				// Es el que contiene la autenticacion?
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}));
		filterChain.doFilter(request, response);
		//ahora vamos a inyectar este filtro en SecurityConfig
	}

	private Optional<String> isValidBearerHeader(String header) {
		String prefix = "Bearer ";
		return header != null && header.startsWith(prefix) ? Optional.of(header.substring(prefix.length())) : Optional.ofNullable(null);
	}

}
