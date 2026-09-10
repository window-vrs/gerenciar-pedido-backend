package br.com.pedido.validator;

import org.springframework.stereotype.Component;

import br.com.pedido.controller.excepton.RegistroNotFoundExcepton;
import br.com.pedido.controller.request.dto.ProdutoDTO;

@Component
public class ProdutoValidator {

	public void validar(ProdutoDTO produto) {
		
		if(produto.getId() == null) {
			throw new RegistroNotFoundExcepton("Informe o id do produto para atualizar");
		}

		//if(produto.getCategoria() == null || produto.getCategoria().getId() == null) {
			//throw new RegistroNotFoundExcepton("Informe a categoria do produto");
		//}
		
		
	}

}
