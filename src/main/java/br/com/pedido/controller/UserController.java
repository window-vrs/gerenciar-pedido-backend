package br.com.pedido.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.controller.request.dto.UserDTO;
import br.com.pedido.controller.response.dto.UserResponseDTO;
import br.com.pedido.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements GenericController {
	
	private final UserService service;

	@GetMapping("/recuperar/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<UserResponseDTO> recuperar(@PathVariable Integer id){
		return service.recuperar(id);
	}
	
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<List<UserResponseDTO>> listar(){
		return service.listar();
	}

	@GetMapping("pesquisar")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<List<UserResponseDTO>> pesquisar(
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) Boolean status){
		return service.pesquisarByExemple(userName, status);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<Object> salvar(@RequestBody @Validated UserDTO user){
		
		ResponseEntity<?> salvar = service.salvar(user);
		
		return ResponseEntity.created(salvar.getHeaders().getLocation()).body(salvar.getBody());
	}
	
	
	@PatchMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'ASISTENTE')")
	public ResponseEntity<Object> atualizar(@RequestBody @Validated UserDTO user){ 
		return service.atualizar(user);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
	public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){ 
		return service.deletar(id);
	}
	

}
