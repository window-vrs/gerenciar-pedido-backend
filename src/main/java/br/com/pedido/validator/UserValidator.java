package br.com.pedido.validator;

import org.springframework.stereotype.Component;

import br.com.pedido.controller.excepton.RegistroDuplicadoExcepton;
import br.com.pedido.controller.excepton.RegistroNotFoundExcepton;
import br.com.pedido.controller.request.dto.UserDTO;
import br.com.pedido.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidator {
	
	private final UserRepository repository;

	public void existeUsuario(UserDTO user) {
		if(existeUser(user)) {
			throw new RegistroDuplicadoExcepton("Usuario já castrado com o mesmo nome de usuário: " + user.getUserName());
		}
		
	}

	private boolean existeUser(UserDTO user) {
		return repository.existsByUserName(user.getUserName());
	}

	public void validarUpdate(UserDTO userDTO) {
		if(userDTO.getId() == null) {
			throw new RegistroNotFoundExcepton("Informe o id do usuário para atualizar");
		}
		
	}
}
