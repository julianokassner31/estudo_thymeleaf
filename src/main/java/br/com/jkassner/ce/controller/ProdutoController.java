package br.com.jkassner.ce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.jkassner.ce.exceptions.ProdutoNotFoundException;
import br.com.jkassner.ce.model.produto.Produto;
import br.com.jkassner.ce.model.produto.ProdutoDto;
import br.com.jkassner.ce.service.produto.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/listar")
	public ModelAndView listar(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String query, 
			@RequestParam(defaultValue = "false") boolean ativo) {
		
		Page<Produto> pageProdutos = produtoService.findAll(query, ativo,
				PageRequest.of(page, size, Sort.by("dataCadastro")));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produtos", pageProdutos.getContent());
		modelAndView.addObject("query", query);
		modelAndView.addObject("page", page);
		modelAndView.addObject("size", size);
		modelAndView.addObject("ativo", ativo);
		modelAndView.addObject("totalPaginas", pageProdutos.getTotalPages());
		modelAndView.addObject("totalProdutos", pageProdutos.getTotalElements());
		modelAndView.setViewName("/produto/listar.html");

		return modelAndView;
	}

	@PostMapping("/salvar")
	@PreAuthorize("hasAnyRole('ROLE_ESCRITA', 'ROLE_ADMIN')")
	public ModelAndView salvar(@ModelAttribute Produto produto) throws ProdutoNotFoundException {

		produtoService.save(produto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/produto/listar");

		return modelAndView;
	}

	@GetMapping("/cadastrar")
	@PreAuthorize("hasAnyRole('ROLE_ESCRITA', 'ROLE_ADMIN')")
	public ModelAndView cadastro() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produto", new Produto());
		modelAndView.setViewName("/produto/cadastrar.html");

		return modelAndView;
	}

	@GetMapping(value = "/editar/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ESCRITA', 'ROLE_ADMIN')")
	public ModelAndView editar(@PathVariable Long id) throws ProdutoNotFoundException {

		Produto produto = produtoService.findById(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produto", produto);
		modelAndView.setViewName("/produto/cadastrar.html");

		return modelAndView;
	}
	
	@GetMapping
	public ResponseEntity<?> findByNome(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "") String nome) {

		List<ProdutoDto> produtos = produtoService.findByNomeLike(nome, PageRequest.of(page, size));

		return ResponseEntity.ok(produtos);
	}
}
