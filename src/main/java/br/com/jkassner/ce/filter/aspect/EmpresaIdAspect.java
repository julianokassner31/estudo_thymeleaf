package br.com.jkassner.ce.filter.aspect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmpresaIdAspect {

	@PersistenceContext
	private EntityManager entityManager;

	@Before("execution(* cloud.lyceum.api.pessoa.repository.*.find*(..))" + "||"
			+ "execution(* cloud.lyceum.api.pessoa.repository.*.delete*(..))")
	public void beforeFindOrDeleteRepository() {
		final String idTenant = "";
		entityManager
			.unwrap(Session.class)
			.enableFilter("tenantFilter")
			.setParameter("idTenant", idTenant);
	}
}
