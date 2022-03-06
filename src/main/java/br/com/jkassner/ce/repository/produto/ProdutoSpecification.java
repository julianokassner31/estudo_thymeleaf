package br.com.jkassner.ce.repository.produto;

import org.springframework.data.jpa.domain.Specification;

import br.com.jkassner.ce.model.Empresa;
import br.com.jkassner.ce.model.produto.Produto;
import br.com.jkassner.ce.model.produto.Produto_;

public class ProdutoSpecification {

	public static Specification<Produto> nome(String nome) {
		return (root, query, builder) -> {
			
			if (nome != null) {
				return builder.like(root.get(Produto_.nome), "%" + nome + "%");
			} else {
				return builder.isNotNull(root.get(Produto_.nome));
			}
		};
	}
	
	public static Specification<Produto> ativo(Boolean ativo) {
		return (root, query, builder) -> {
			
			if (ativo) {
				return builder.equal(root.get(Produto_.ativo), ativo);
			} else {
				return builder.isNotNull(root.get(Produto_.ativo));
			}
		};
	}
	
	public static Specification<Produto> empresa(Empresa empresa) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Produto_.empresa), empresa);
		};
	}
}
