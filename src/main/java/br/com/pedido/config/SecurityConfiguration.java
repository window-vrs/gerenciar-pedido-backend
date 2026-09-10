package br.com.pedido.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.pedido.security.CustomUserDetailsServices;
import br.com.pedido.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((AbstractHttpConfigurer:: disable))
		.httpBasic(Customizer.withDefaults())
		.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/autenticacao/**").permitAll();
			authorize.requestMatchers(
					 "/v3/api-docs/**",
	                    "/swagger-ui/**",
	                    "/swagger-ui.html").permitAll();
			
			//authorize.requestMatchers(HttpMethod.PATCH, "/user/**").hasAnyRole("ADMIN", "GESTOR", "USER");
			//authorize.requestMatchers("/user/**").hasRole("ADMIN");
			//authorize.requestMatchers(HttpMethod.POST, "/api/v1/user/**").permitAll();
			//authorize.requestMatchers(HttpMethod.PATCH, "/api/v1/user/**").permitAll();
			//authorize.requestMatchers(HttpMethod.POST, "/api/v1/produto/**").hasRole("ADMIN");
			//authorize.requestMatchers(HttpMethod.PUT, "/api/v1/produto/**").hasAnyRole("ADMIN", "GESTOR");
			//authorize.requestMatchers(HttpMethod.DELETE, "/api/v1/produto/**").hasRole("ADMIN");
			//authorize.requestMatchers("/api/v1/user/**").hasAnyRole("USER", "ADMIN");
			
			authorize.anyRequest().authenticated();
		});
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserService userService) {
	
/**
		UserDetails user1 = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("123"))
				.roles("ADMIN")
				.build();
		
		
		UserDetails user2 = User.builder()
				.username("vrs")
				.password(passwordEncoder.encode("123"))
				.roles("USER")
				.build();
		
		
		return new InMemoryUserDetailsManager(user1, user2);
**/
		
	 return	new CustomUserDetailsServices(userService);	
		
	}
	

}
