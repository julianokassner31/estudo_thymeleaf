package br.com.jkassner.ce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.jkassner.ce.model.Estoque;
import br.com.jkassner.ce.model.TipoLancamentoEnum;
import br.com.jkassner.ce.service.estoque.EstoqueService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@GetMapping(value="/buscar")
	public ModelAndView listarPorIdProduto(
			@RequestParam(required = true) Long produto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = true) String nome) {
		
		Page<Estoque> pageEstoque = estoqueService.findAll(produto, PageRequest.of(page, size));
		BigDecimal totalEstoque = estoqueService.sum(produto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("estoqueList", pageEstoque.getContent());
		modelAndView.addObject("totalEstoque", totalEstoque);
		modelAndView.addObject("totalPaginas", pageEstoque.getTotalPages());
		modelAndView.addObject("nome", nome);
		modelAndView.addObject("produto", produto);
		modelAndView.addObject("page", page);
		modelAndView.addObject("size", size);
		
		modelAndView.setViewName("/estoque/listar.html");
		
		return modelAndView;
	}
	
	@GetMapping(value="/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/estoque/listar.html");
		
		return modelAndView;
	}
	
	@GetMapping(value="/lancamento")
	@PreAuthorize("hasAnyRole('ROLE_ESCRITA', 'ROLE_ADMIN')")
	public ModelAndView lancamento() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tipoLancamentos", TipoLancamentoEnum.values());
		modelAndView.setViewName("/estoque/lancar.html");
		
		return modelAndView;
	}
	
	@PostMapping(value="/lancar")
	@PreAuthorize("hasAnyRole('ROLE_ESCRITA', 'ROLE_ADMIN')")
	public ModelAndView lancar(@ModelAttribute Estoque estoque) {
		
		estoqueService.save(estoque);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msgSuccess", "Salvo com sucesso.");
		modelAndView.addObject("tipoLancamentos", TipoLancamentoEnum.values());
		modelAndView.setViewName("/estoque/lancar.html");
		
		return modelAndView;
	}
}
