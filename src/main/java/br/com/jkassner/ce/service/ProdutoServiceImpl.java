package br.com.jkassner.ce.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.jkassner.ce.exceptions.ProdutoNotFoundException;
import br.com.jkassner.ce.model.Usuario;
import br.com.jkassner.ce.model.produto.Produto;
import br.com.jkassner.ce.repository.produto.ProdutoRepository;
import br.com.jkassner.ce.repository.produto.ProdutoSpecification;
import br.com.jkassner.ce.utils.UsuarioLogadoUtils;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void save(Produto produto) throws ProdutoNotFoundException {
		
		if (produto.getId() == null) {
			Usuario usuario = UsuarioLogadoUtils.getUsuario();
			produto.setEmpresa(usuario.getEmpresa());
			produto.setUsuario(usuario);
			produto.setDataCadastro(LocalDateTime.now());
		} else {
			Produto findById = produtoRepository.findById(produto.getId()).orElseThrow(ProdutoNotFoundException::new);
			produto.setEmpresa(findById.getEmpresa());
			produto.setUsuario(findById.getUsuario());
			produto.setDataCadastro(findById.getDataCadastro());
		}
		
		produtoRepository.save(produto);
	}

	@Override
	public Page<Produto> findAll(String nome, Boolean ativo, Pageable page) {
		Usuario usuario = UsuarioLogadoUtils.getUsuario();

		Specification<Produto> spec = Specification.where(
				ProdutoSpecification.empresa(usuario.getEmpresa())
				.and(ProdutoSpecification.nome(nome))
				.and(ProdutoSpecification.ativo(ativo))
			);

		return produtoRepository.findAll(spec, page);
	}

	@Override
	public Produto findById(Long idProduto) throws ProdutoNotFoundException {
		return produtoRepository.findById(idProduto).orElseThrow(ProdutoNotFoundException::new);
	}
}
