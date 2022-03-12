package br.com.jkassner.ce.service.estoque;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jkassner.ce.model.Estoque;
import br.com.jkassner.ce.model.TipoLancamentoEnum;
import br.com.jkassner.ce.repository.estoque.EstoqueRepository;
import br.com.jkassner.ce.utils.UsuarioLogadoUtils;

@Service
public class EstoqueServiceImpl implements EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public Page<Estoque> findAll(Long idProduto, Pageable page) {
		
		return estoqueRepository.findAllByProdutoId(idProduto, page);
	}
	
	public void save(Estoque estoque) {
		
		estoque.setDataLancamento(LocalDateTime.now());
		estoque.setUsuario(UsuarioLogadoUtils.getUsuario());
		
		if (estoque.getTipoLancamento().equals(TipoLancamentoEnum.SAIDA)) {
			estoque.setQuantidade(estoque.getQuantidade().negate());
		}
		
		estoqueRepository.save(estoque);
	}
	
	public BigDecimal sum(Long idProduto) {
		
		return estoqueRepository.sum(idProduto);
	}
}
