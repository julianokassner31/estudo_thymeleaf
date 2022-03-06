package br.com.jkassner.ce.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.jkassner.ce.model.Usuario;

public class UsuarioLogadoUtils {
	
	public static Usuario getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) authentication.getPrincipal();
	}

}
