package br.com.pedido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.controller.request.dto.AutenticacaoDTO;
import br.com.pedido.controller.response.dto.UserResponseDTO;
import br.com.pedido.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController implements GenericController {
	
	private final UserService service;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> autenticar(@RequestBody @Validated AutenticacaoDTO auAtenticacaoDTO){ 
		return service.autenticar(auAtenticacaoDTO);
	}
	

}
