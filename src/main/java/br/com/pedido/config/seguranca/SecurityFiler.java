package br.com.pedido.config.seguranca;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pedido.repository.UserRepository;
import br.com.pedido.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFiler extends OncePerRequestFilter{
	
	private final TokenService tokenService;
	private final UserRepository repository;

	@Order(1)
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		var token = recoverToken(request);
		if (token != null) {
			var login = tokenService.validarToken(token);
			UserDetails user = repository.findByUsername(login);
			
			var authentication = new UsernamePasswordAuthenticationToken(login, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var httpHeader = request.getHeader("Authorization");
		
		if (httpHeader == null) {
			return null;
		}
		return httpHeader.replace("Bearer ", "");
	}
	
	

}
