package br.com.pedido.controller.request.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "nome", "descricao" })
public class RolesUserDTO {

	private Integer id;
	private String nome;
	private String descricao;

}
