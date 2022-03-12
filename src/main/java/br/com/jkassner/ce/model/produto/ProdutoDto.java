package br.com.jkassner.ce.model.produto;

import org.springframework.beans.factory.annotation.Value;

public interface ProdutoDto {
	@Value("#{target.id}")
	Long getId();
	@Value("#{target.nome}")
	String getNome();
}
