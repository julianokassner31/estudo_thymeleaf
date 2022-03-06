package br.com.jkassner.ce.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jkassner.ce.model.Role;
import br.com.jkassner.ce.model.TipoRole;
import br.com.jkassner.ce.model.Usuario;
import br.com.jkassner.ce.repository.RoleRepository;
import br.com.jkassner.ce.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired RoleRepository roleRepository;

	@Override
	public List<Usuario> listar() {
		
		return usuarioRepository.findAll();
	}
	
	@Override
	public Usuario salvar(Usuario usuario) {
		encryptSenha(usuario);
		setEmpresaInUsuario(usuario);
		usuario.setDataCadastro(LocalDateTime.now());
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		if(usuario == null) throw new UsernameNotFoundException(login);
		
		return usuario;
	}
	
	private void encryptSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		String encode = new BCryptPasswordEncoder().encode(senha);
		usuario.setSenha(encode);
	}
	
	private void setEmpresaInUsuario(Usuario usuario) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuariLogado = (Usuario) authentication.getPrincipal();
		usuario.setEmpresa(usuariLogado.getEmpresa());
	}

	@Override
	public List<Role> getRoles() {
		SecurityContext context = SecurityContextHolder.getContext();
		Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();

		if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals(TipoRole.ROLE_ADMIN.name()))) {
			
			return roleRepository.findAll();
		}
		
		return roleRepository.findByTipoNot(TipoRole.ROLE_ADMIN);
	}
}
