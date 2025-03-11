package com.example.apiwistonspring.configuration.security.jwt;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtils {
	// clave para asegurar que el token es real, porque llevara esta firma
//	EncryptedKeyGenerator en web 256 en hex https://asecuritysite.com/encryption/plain
	@Value("${jwt.secret.key}")
	private String secretKey;
	@Value("${jwt.time.expiration}")
	private String timeExpiration;

	/*
	 * https://jwt.io/
	 */

	// creador de tokens
	public String generateAccessToken(String username) {
		System.out.println("JWTUtils:generando access toekn");
		return Jwts.builder()
				// Cuidao date de sql
//				.issuedAt(Date.valueOf(LocalDate.now().plus(1, ChronoUnit.DAYS)))
				.issuedAt(Date.valueOf(LocalDate.now()))
				.expiration(Date.from(LocalDateTime.now().plusSeconds(30).atZone(ZoneId.systemDefault()).toInstant()))
//				.expiration(Date.valueOf(LocalDate.now().plus(30,ChronoUnit.SECONDS)))
				.subject(username)
				// algoritmo por defecto HS256
				.signWith(getSignatureKey())
				.compact();
	}
	public String generateRefreshToken(User user) {
		return Jwts.builder()
				.issuedAt(Date.valueOf(LocalDate.now()))
				.subject(user.getUsername())
                .signWith(getSignatureKey()).compact();
	}
	// ValidarToken
	public Boolean isTokenValid(String token) {
		try {
			getAllClaims(token);
			return true;
		} catch (Exception e) {
			log.error("JWTUtils:token invalido " + e.getMessage());
			return false;
		}
	}
 
	public String getUSerNameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	private <T> T getClaim(String token, Function<Claims, T> claimsFunctin) {
		Claims allClaims = getAllClaims(token);
		return claimsFunctin.apply(allClaims);
	}

	private Claims getAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignatureKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	// get firma del token un valor codificado basado en la secretKey
	public SecretKey getSignatureKey() {
		/*
		 * Aquí se está utilizando la clase Keys del paquete javax.crypto.spec para
		 * generar una clave HMAC-SHA utilizando la secretKey. La secretKey se
		 * decodifica de Base64 usando Decoders.BASE64.decode(secretKey), que se espera
		 * que sea una cadena codificada en Base64 que representa la clave secreta.
		 * 
		 * En resumen, este método getSignatureKey() genera una clave para firmar
		 * utilizando HMAC-SHA a partir de una clave secreta codificada en Base64
		 * llamada secretKey. //aqui vamos a crear la firma sha de la secretKey observa
		 * como se decodifica la secretkey
		 */
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
}

