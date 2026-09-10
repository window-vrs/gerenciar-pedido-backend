package br.com.pedido.controller.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticacaoDTO {

	@NotBlank(message = "O campo username não pode ser nulo")
	@Size(min = 3, max = 20, message = "O campo username deve ter entre 3 e 20 caracteres")
	private String userName;
	
	@NotNull(message = "O campo password não pode ser nulo")
	@Size(min = 6, max = 60, message = "O campo password deve ter entre 6 e 20 caracteres")
	private String password;

}
