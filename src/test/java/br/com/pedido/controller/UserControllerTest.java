package br.com.pedido.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.pedido.config.SecurityConfiguration;
import br.com.pedido.controller.request.dto.UserDTO;
import br.com.pedido.controller.response.dto.UserResponseDTO;
import br.com.pedido.repository.RoleRepository;
import br.com.pedido.repository.UserRepository;
import br.com.pedido.service.UserService;
import br.com.pedido.service.filtro.FiiltroUsuario;
import br.com.pedido.util.CustomizationBeanUtils;
import br.com.pedido.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityConfiguration.class})
@Slf4j
public class UserControllerTest {

	//private final ApplicationContext context;
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockitoBean 
	private UserService userService;

	@MockitoBean
	private UserRepository repository;
	@MockitoBean
	private RoleRepository roleRepository;
	@MockitoBean
	private CustomizationBeanUtils customizationBeanUtils;
	@MockitoBean
	private ModelMapper modelMapper;
	@MockitoBean
	private UserValidator validator;
	@MockitoBean
	private PasswordEncoder passwordEncoder;
	@MockitoBean
	private FiiltroUsuario exempleUsuario;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	void recuperarSucessoTest() throws Exception {
		String jsonRequest = createJsonRequest();
		
		UserResponseDTO userResponseDTO = createUserDTOResponse();
		
		when(userService.recuperar(1)).thenReturn(ResponseEntity.ok(userResponseDTO));
		
		ResultActions result = mvc.perform(get("/user/recuperar/1")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.userName").value("maria.silva"));
	}


	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	void listarTodosTestSucesso() throws Exception {
		
		ResponseEntity<List<UserResponseDTO>> listaUsers = popularListaUsers();
		
		when(userService.listar()).thenReturn(listaUsers);
		
		ResultActions result = mvc.perform(get("/user")
				.contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		   .andExpect(jsonPath("$[0].id").value(1))
		   .andExpect(jsonPath("$[0].userName").value("maria.silva"));
	}



	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	void pesquisarTest() throws Exception {
		ResponseEntity<List<UserResponseDTO>> listaUsers = popularListaUsers();
		
		when(userService.pesquisarByExemple(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(listaUsers);
		ResultActions result = mvc.perform(get("/user/pesquisar")
				.contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("$[0].id").value(1))
		   .andExpect(jsonPath("$[0].userName").value("maria.silva"));
		
	}

	
	private UserResponseDTO createUserDTOResponse() {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(1);
		userResponseDTO.setUserName("maria.silva");
		userResponseDTO.setStatus(true);
		return userResponseDTO;
	}

	private UserDTO createUserDTORequest() {
		UserDTO userRequestDTO = new UserDTO();
		userRequestDTO.setId(1);
		userRequestDTO.setUserName("maria.silva");
		userRequestDTO.setStatus(true);
		userRequestDTO.setPassword("123456");
		return userRequestDTO;
	}

	private String createJsonRequest() {
		return mapper.writeValueAsString(createUserDTORequest());
	}


	private ResponseEntity<List<UserResponseDTO>> popularListaUsers() {
		return ResponseEntity.ok(List.of(createUserDTOResponse()));
	}
	
}
