package br.com.jkassner.ce.service.estoque;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jkassner.ce.model.Estoque;

public interface EstoqueService {
	
	Page<Estoque> findAll(Long idProduto, Pageable page);
	
	void save(Estoque estoque);
	
	BigDecimal sum(Long idProduto);

}
