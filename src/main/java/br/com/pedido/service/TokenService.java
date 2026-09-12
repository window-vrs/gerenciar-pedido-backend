package br.com.pedido.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.pedido.controller.excepton.ErrorTokenExcepton;
import br.com.pedido.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	
	@Value("${api.security.jwt.secret}")
	private String secrets;

	
	public String gerarToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secrets);
			
			return JWT.create()
					.withIssuer("auth-api-pedido")
					.withSubject(user.getUsername())
					.withExpiresAt(generateExpirationDate())
					.sign(algorithm);
			
		} catch (JWTCreationException e) {
			throw new ErrorTokenExcepton("Não foi possível gerar o token JWT");
		}
	}
	
	
	public String validarToken(String token) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secrets);
			
			return JWT.require(algorithm)
					.withIssuer("auth-api-pedido")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTCreationException e) {
			return "";
		}
		
	}


	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant();
	}
	

}
