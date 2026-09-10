package br.com.pedido.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pedido.controller.excepton.ErroResposta;
import br.com.pedido.controller.excepton.RegistroDuplicadoExcepton;
import br.com.pedido.controller.request.dto.ProdutoDTO;
import br.com.pedido.controller.response.dto.ProdutoResponseDTO;
import br.com.pedido.model.Produto;
import br.com.pedido.repository.ProdutoRepository;
import br.com.pedido.service.filtro.FiltroProduto;
import br.com.pedido.util.CustomizationBeanUtils;
import br.com.pedido.validator.ProdutoValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProdutoService {


	private final ProdutoRepository repository;
	private final ModelMapper modelMapper;
	private final ProdutoValidator validator;
	private final FiltroProduto filtroProduto;
	private final CustomizationBeanUtils customizationBeanUtils;

	
	public ResponseEntity<List<ProdutoResponseDTO>> listar() {
		List<Produto> all = repository.findAll();
		
		
		List<ProdutoResponseDTO> listaProdutos = all.stream().map(Produto -> {
			return convertEntityToDTO(Produto);
		}).toList();
		
		
		return listaProdutos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaProdutos);
	}


	private ProdutoResponseDTO convertEntityToDTO(Produto Produto) {
		return modelMapper.map(Produto, ProdutoResponseDTO.class);
	}

	private Produto convertDTOToEntity(ProdutoDTO dto) {
		return modelMapper.map(dto, Produto.class);
	}


	public ResponseEntity<Object> salvar(ProdutoDTO Produto) {
		
		try {
			this.validator.validar(Produto);
			Produto save = repository.save(modelMapper.map(Produto, Produto.class));

			return ResponseEntity.ok(convertEntityToDTO(save));
		} catch (RegistroDuplicadoExcepton e) {
			
			var erroDto = ErroResposta.conflito(e.getMessage());
			
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
	}


	public ResponseEntity<Void> deletar(Integer id) {
		
		Optional<Produto> byId = repository.findById(id);
		
		if(byId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}


	public ResponseEntity<Object> atualizar(ProdutoDTO produtoDTO) {
		
		validator.validar(produtoDTO);
		
		Produto produtoRecuperado = repository.findById(produtoDTO.getId()).get();
		Produto entity = convertDTOToEntity(produtoDTO);
		
		try {
			customizationBeanUtils.copyProperties(produtoRecuperado, entity);
		} catch (IllegalAccessException e) {
			
			var erroDto = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		} catch (InvocationTargetException e) {
			
			var erroDto = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
		
		return ResponseEntity.ok(convertEntityToDTO(repository.save(produtoRecuperado)));
	}


	public ResponseEntity<List<ProdutoResponseDTO>> pesquisar(String ProdutoName, Boolean status) {
		List<Produto> all = new ArrayList<>(); 
		
			
		
		List<ProdutoResponseDTO> listaProdutos = all.stream().map(Produto -> {
			return convertEntityToDTO(Produto);
		}).toList();
		
		return listaProdutos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaProdutos);
	}

	public ResponseEntity<List<ProdutoResponseDTO>> pesquisarByEspecification(String nomeProduto, Boolean status, String descricao, Integer anoCadastro, Integer anoAtualizacao) {
		List<Produto> all = new ArrayList<>(); 
		
		Specification<Produto> espec = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(StringUtils.isNotBlank(nomeProduto)) {
			espec = espec.and(filtroProduto.nomeLike(nomeProduto));
		}
		if(StringUtils.isNotBlank(descricao)) {
			espec = espec.and(filtroProduto.descricao(descricao));
		}

		if(anoCadastro != null) {
			espec = espec.and(filtroProduto.anoCadastro(anoCadastro));
		}
		if(anoAtualizacao != null) {
			espec = espec.and(filtroProduto.anoAtualizacao(anoAtualizacao));
		}
		
		
		all = repository.findAll(espec);
		
		List<ProdutoResponseDTO> listaProdutos = all.stream().map(Produto -> {
			return convertEntityToDTO(Produto);
		}).toList();
		
		return listaProdutos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaProdutos);
	}

	public ResponseEntity<Page<ProdutoResponseDTO>> pesquisaPaginada(String nomeProduto, Boolean status, String descricao, Integer anoCadastro, Integer anoAtualizacao, Integer tamanhoPagina, Integer pagina) {
		
		Specification<Produto> espec = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(StringUtils.isNotBlank(nomeProduto)) {
			espec = espec.and(filtroProduto.nomeLike(nomeProduto));
		}
		if(StringUtils.isNotBlank(descricao)) {
			espec = espec.and(filtroProduto.nomeLike(descricao));
		}
		
		if(anoCadastro != null) {
			espec = espec.and(filtroProduto.anoCadastro(anoCadastro));
		}
		if(anoAtualizacao != null) {
			espec = espec.and(filtroProduto.anoAtualizacao(anoAtualizacao));
		}
		
		
		Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina); 
		var resultado =  repository.findAll(espec, pageRequest);

		return ResponseEntity.ok(resultado.map(this::convertEntityToDTO));
	}


	public ResponseEntity<ProdutoResponseDTO> recuperar(Integer id) {
		return repository.findById(id)
				.map(produto -> ResponseEntity.ok(convertEntityToDTO(produto)))
				.orElse(ResponseEntity.notFound().build());
	}



}
