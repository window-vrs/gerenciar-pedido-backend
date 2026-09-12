package br.com.pedido.service.filtro;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.pedido.model.Produto;

@Component
public class FiltroProduto {
	
	public Specification<Produto> nomeIqual(String nome) {
		return (root, query, cb) -> cb.equal(root.get("nome"), nome);
	}
	
	public Specification<Produto> nomeLike(String nome) {
		return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%"+nome.toUpperCase()+"%");
	}

	public Specification<Produto> descricao(String descricao) {
		return (root, query, cb) -> cb.like(cb.upper(root.get("descricao")), "%"+descricao.toUpperCase()+"%");
	}

	public Specification<Produto> anoCadastro(Integer ano) {
		return (root, query, cb) -> 
		        cb.equal( cb.function("YEAR", Integer.class, root.get("dataCadastro")), ano);
	}

	public Specification<Produto> anoAtualizacao(Integer ano) {
		return (root, query, cb) -> 
		
		 //Join<Produto, Categoria> enderecoJoin = root.join("catetoria", JoinType.INNER);   
		 
		 
		
		cb.equal(cb.function("YEAR", Integer.class, root.get("atualizacao"), cb.literal("YYYY")), ano);
	}


}
