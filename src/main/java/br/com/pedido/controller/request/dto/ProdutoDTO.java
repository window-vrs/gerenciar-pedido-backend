package br.com.pedido.controller.request.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProdutoDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoque;
    private String codigoBarras;
    private Boolean status;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
	private CategoriaProdutoDTO categoria;
	
}
