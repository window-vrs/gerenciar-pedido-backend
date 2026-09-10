package br.com.pedido.controller.request.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

	private Integer id;
	
	@NotBlank(message = "O campo username não pode ser nulo")
	@Size(min = 3, max = 20, message = "O campo username deve ter entre 3 e 20 caracteres")
	private String userName;
	
	@NotNull(message = "O campo password não pode ser nulo")
	@Size(min = 6, max = 255, message = "O campo password deve ter entre 6 e 20 caracteres")
	private String password;

	private String observacao;
	
	@NotNull(message = "O campo status não pode ser nulo")
	private Boolean status;
	
	private List<RolesUserDTO> roles;
	
}
