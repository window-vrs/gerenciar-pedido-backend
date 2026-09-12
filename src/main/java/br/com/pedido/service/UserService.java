package br.com.pedido.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pedido.controller.excepton.ErroResposta;
import br.com.pedido.controller.excepton.RegistroDuplicadoExcepton;
import br.com.pedido.controller.excepton.RegistroNotFoundExcepton;
import br.com.pedido.controller.request.dto.AutenticacaoDTO;
import br.com.pedido.controller.request.dto.UserDTO;
import br.com.pedido.controller.response.dto.UserResponseDTO;
import br.com.pedido.model.Role;
import br.com.pedido.model.User;
import br.com.pedido.repository.RoleRepository;
import br.com.pedido.repository.UserRepository;
import br.com.pedido.service.filtro.FiiltroUsuario;
import br.com.pedido.util.CustomizationBeanUtils;
import br.com.pedido.validator.UserValidator;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService{

	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final CustomizationBeanUtils customizationBeanUtils;
	private final ModelMapper modelMapper;
	private final UserValidator validator;
	private final PasswordEncoder passwordEncoder;
	private final FiiltroUsuario exempleUsuario;

	public ResponseEntity<UserResponseDTO> recuperar(Integer id) {
		return repository.findById(id)
				.map(usuario -> ResponseEntity.ok(converteEntityResponseToDTO(usuario)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	public ResponseEntity<List<UserResponseDTO>> listar() {
		List<User> all = repository.findAll();
		
		List<UserResponseDTO> listaUsers = all.stream().map(user -> {
			return converteEntityResponseToDTO(user);
		}).toList();
		
		
		return listaUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaUsers);
	}

	public ResponseEntity<Object> salvar(UserDTO user) {
		
		try {
			
			this.validator.existeUsuario(user);
			
			User userEntity = modelMapper.map(user, User.class);
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			
			User save = repository.save(userEntity);
			
			return ResponseEntity.ok(converteEntityResponseToDTO(save));
		} catch (RegistroDuplicadoExcepton e) {
			
			var erroDto = ErroResposta.conflito(e.getMessage());
			
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
	}


	public ResponseEntity<Void> deletar(Integer id) {
		
		Optional<User> byId = repository.findById(id);
		
		if(byId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Transactional
	public ResponseEntity<Object> atualizar(UserDTO userDTO) {
		validator.validarUpdate(userDTO);
		
		User usuarioRecuperado = repository.findById(userDTO.getId()).get();
		
		User userEntity =converteDTOToEntity(userDTO);
		
		try {
			customizationBeanUtils.copyProperties(usuarioRecuperado, userEntity);
		} catch (IllegalAccessException e) {
			
			var erroDto = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		} catch (InvocationTargetException e) {
			
			var erroDto = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
		
		usuarioRecuperado.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		List<Role> roles = userDTO.getRoles().stream().map(roleDTO -> {
			return roleRepository.findById(roleDTO.getId()).get();
		}).toList();
		usuarioRecuperado.getRoles().addAll(roles);
		
		return ResponseEntity.status(HttpStatus.OK).body(converteEntityResponseToDTO(repository.save(usuarioRecuperado)));
	}

	private User converteDTOToEntity(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}
	private UserResponseDTO converteEntityResponseToDTO(User user) {
		return modelMapper.map(user, UserResponseDTO.class);
	}
	

	public ResponseEntity<List<UserResponseDTO>> pesquisar(String userName, Boolean status) {
		List<User> all = new ArrayList<>(); 
		
		if(StringUtils.isNotBlank(userName)) {
			all = repository.listByUsername(userName);
		}else if(status != null) {
			all = repository.findBystatus(status);
		}else {
			all = repository.findAll();
		}
		List<UserResponseDTO> listaUsers = all.stream().map(user -> {
			return converteEntityResponseToDTO(user);
		}).toList();
		
		return listaUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaUsers);
	}

	public ResponseEntity<List<UserResponseDTO>> pesquisarByExemple(String userName, Boolean status) {
		List<User> all = new ArrayList<>(); 

		all = repository.findAll(exempleUsuario.pesquisar(userName, status));
		
		List<UserResponseDTO> listaUsers = all.stream().map(user -> {
			return converteEntityResponseToDTO(user);
		}).toList();
		
		return listaUsers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaUsers);
	}


	public User findByUserName(String username) {
		return repository.findByUsername(username);
	}


	public ResponseEntity<UserResponseDTO> autenticar(AutenticacaoDTO autenticacaoDTO) {
		
		 Optional<User> user = repository.findByUsernameAndPassword(autenticacaoDTO.getUsername(), autenticacaoDTO.getPassword());
		 if(user.isEmpty()) {
			 throw new RegistroNotFoundExcepton("Usuário não encontrado");
		 }
		
		
		return ResponseEntity.ok(converteEntityResponseToDTO(user.get()));
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username);
	}



}
