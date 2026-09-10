package br.com.pedido.controller.request.dto;

import lombok.Data;

@Data
public class CategoriaProdutoDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Boolean status;
	
}
