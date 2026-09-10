package br.com.pedido.service.filtro;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import br.com.pedido.model.User;

@Component
public class FiiltroUsuario {

	public Example<User> pesquisar(String userName, Boolean status) {
		var userParam = new User();
		userParam.setUserName(userName);
		userParam.setStatus(status);
		
		ExampleMatcher matching = ExampleMatcher.matching();
		
		matching = matching.withIgnoreNullValues();
		matching = matching.withIgnoreCase();
		matching = matching.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<User> exemple = Example.of(userParam, matching);
		return exemple;
	}

}
