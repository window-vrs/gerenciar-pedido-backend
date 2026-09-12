package br.com.pedido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.controller.request.dto.AutenticacaoDTO;
import br.com.pedido.controller.response.dto.LoginResponseDTO;
import br.com.pedido.model.User;
import br.com.pedido.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
@Slf4j
public class AutenticacaoController implements GenericController {
	
	private final AuthenticationManager autenticationManager;
	private final TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<LoginResponseDTO> autenticar(@RequestBody @Validated AutenticacaoDTO auAtenticacaoDTO){ 
	   log.info("Autenticando usuário: {}", auAtenticacaoDTO.getUsername());
	   
		var userNamePassword = new UsernamePasswordAuthenticationToken(auAtenticacaoDTO.getUsername(), auAtenticacaoDTO.getPassword());
		
		var auth = autenticationManager.authenticate(userNamePassword);
		var token = tokenService.gerarToken((User) auth.getPrincipal());
		
		
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	

}
