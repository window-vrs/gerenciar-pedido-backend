package br.com.pedido.controller.response.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.pedido.controller.request.dto.RolesUserDTO;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "username", "password", "dataCadastro", "status" })
public class UserResponseDTO {
	
	private Integer id;
	private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCadastro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime atualizacao;
	private Boolean status;
	
	private List<RolesUserDTO> roles;
}
