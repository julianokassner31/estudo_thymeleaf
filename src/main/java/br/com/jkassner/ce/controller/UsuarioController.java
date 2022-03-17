package br.com.jkassner.ce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jkassner.ce.model.Usuario;
import br.com.jkassner.ce.service.usuario.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private static final String LISTAR_USUARIOS = "/usuario/listar_usuarios.html";

	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/listar")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView listar() {
		
		List<Usuario> usuarios = usuarioService.listar();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.addObject("roles", usuarioService.getRoles());
		modelAndView.setViewName(LISTAR_USUARIOS);
		
		return modelAndView; 
	}
	
	@PostMapping(value = "/cadastrar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ESCRITA')")
	public ModelAndView cadastrar(@ModelAttribute Usuario usuario) {
		
		usuarioService.salvar(usuario);
		
		return new ModelAndView("redirect:/usuario/listar");
	}
	
	@PostMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ESCRITA')")
	public ModelAndView deletar(@PathVariable(value = "id") Long id) {
		
		usuarioService.deletar(id);
		
		return new ModelAndView("redirect:/usuario/listar");
	}
	
	@PostMapping(value="/logout")
	public ModelAndView logout() {
		SecurityContextHolder.clearContext();
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/roles")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ESCRITA')")
	public ResponseEntity<?> getRoles() {
		return ResponseEntity.ok(usuarioService.getRoles());
	}
}
