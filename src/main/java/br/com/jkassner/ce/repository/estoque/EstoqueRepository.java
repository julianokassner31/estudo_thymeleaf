package br.com.jkassner.ce.repository.estoque;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jkassner.ce.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

	Page<Estoque> findAllByProdutoId(Long idProduto, Pageable page);

	@Query("select sum(e.quantidade) from Estoque e where e.produto.id = :idProduto")
	BigDecimal sum(@Param(value = "idProduto") Long idProduto);
}
