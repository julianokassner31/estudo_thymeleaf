package br.com.jkassner.ce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.jkassner.ce.model.Role;
import br.com.jkassner.ce.model.Usuario;

public interface UsuarioService extends UserDetailsService {

	Usuario salvar(Usuario usuario);
	
	void deletar(Long id);
	
	List<Usuario> listar();
	
	List<Role> getRoles();

}
