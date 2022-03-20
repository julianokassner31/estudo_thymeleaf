package br.com.jkassner.ce.filter.aspect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import br.com.jkassner.ce.utils.UsuarioLogadoUtils;

@Aspect
@Component
public class EmpresaIdAspect {

	@PersistenceContext
	private EntityManager entityManager;

	@Before(value = "execution(* br.com.jkassner.ce.repository.produto.*.find*(..))" + "||"
			+ "execution(* br.com.jkassner.ce.repository.produto.*.delete*(..))")
	public void beforeFindOrDeleteRepository() {
		
		entityManager
			.unwrap(Session.class)
			.enableFilter("empresaIdFilter")
			.setParameter("empresa", UsuarioLogadoUtils.getUsuario().getEmpresa().getId());
	}
}
