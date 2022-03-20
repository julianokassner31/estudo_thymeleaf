package br.com.jkassner.ce.repository.produto;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.jkassner.ce.model.produto.Produto;
import br.com.jkassner.ce.model.produto.ProdutoDto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

	public List<ProdutoDto> findByNomeLike(String nome, Pageable page);
}
