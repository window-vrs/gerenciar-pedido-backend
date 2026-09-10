package br.com.pedido.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.pedido.model.User;
import br.com.pedido.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService {
	
	private final UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = service.findByUserName(username);
		if(user == null) {
			new UsernameNotFoundException("User not found with username: " + username);
		}
		
		UserBuilder roles = org.springframework.security.core.userdetails.User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles(user.getRoles().stream().map(role -> role.getNome())
			    .toArray(String[]::new));
		return roles.build();
	}


}
