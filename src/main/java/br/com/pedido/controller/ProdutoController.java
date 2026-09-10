package br.com.pedido.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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

import br.com.pedido.controller.request.dto.ProdutoDTO;
import br.com.pedido.controller.response.dto.ProdutoResponseDTO;
import br.com.pedido.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController implements GenericController {
	
	private final ProdutoService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<List<ProdutoResponseDTO>> listar(){
		return service.listar();
	}

	@GetMapping("/recuperar/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<ProdutoResponseDTO> recuperar(@PathVariable Integer id){
		return service.recuperar(id);
	}

	@GetMapping("pesquisar")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<List<ProdutoResponseDTO>> pesquisar(
			@RequestParam(required = false) String nomeProduto,
			@RequestParam(required = false) Boolean status,
			@RequestParam(required = false) String descricao,
			@RequestParam(required = false) Integer anoCadastro,
			@RequestParam(required = false) Integer anoAtualizacao){

		return service.pesquisarByEspecification(nomeProduto, status, descricao, anoCadastro, anoAtualizacao);
	}

	@GetMapping("pesquisa-paginada")
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR', 'USER', 'ASISTENTE')")
	public ResponseEntity<Page<ProdutoResponseDTO>> pesquisaPaginada(
			@RequestParam(value = "nomeProduto", required = false) String nomeProduto,
			@RequestParam(value = "status", required = false) Boolean status,
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "anoCadastro", required = false) Integer anoCadastro,
			@RequestParam(value = "anoAtualizacao", required = false) Integer anoAtualizacao,
			@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
			@RequestParam(value = "tamanhoPagina", required = false, defaultValue = "10") Integer tamanhoPagina){
		return service.pesquisaPaginada(nomeProduto, status, descricao, anoCadastro, anoAtualizacao, tamanhoPagina, pagina);
		
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
	public ResponseEntity<Object> salvar(@RequestBody @Validated ProdutoDTO Produto){ 
		ResponseEntity<?> salvar = service.salvar(Produto);
		return ResponseEntity.created(salvar.getHeaders().getLocation()).body(salvar.getBody());
	}
	
	
	@PatchMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Object> atualizar(@RequestBody @Validated ProdutoDTO Produto){ 
		return service.atualizar(Produto);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('GESTOR')")
	public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){ 
		return service.deletar(id);
	}
	
}
