package br.com.jkassner.ce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jkassner.ce.exceptions.ProdutoNotFoundException;
import br.com.jkassner.ce.model.produto.Produto;

public interface ProdutoService {

	void save(Produto produto) throws ProdutoNotFoundException;

	Produto findById(Long idProduto) throws ProdutoNotFoundException;

	Page<Produto> findAll(String nome, Boolean ativo, Pageable page);
	
}
