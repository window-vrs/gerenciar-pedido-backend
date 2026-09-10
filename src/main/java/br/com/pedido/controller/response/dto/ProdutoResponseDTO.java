package br.com.pedido.controller.response.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoque;
	private String codigoBarras;
	private String categoria;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCadastro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime atualizacao;
	private Boolean status;
	
}
