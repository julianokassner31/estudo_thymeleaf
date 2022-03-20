package br.com.jkassner.ce.filter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	@Before(value = "execution(* br.com.jkassner.ce.service.produto.ProdutoServiceImpl.find*(..)) and args(nome, ativo, page)")
	public void beforeAdvice(JoinPoint joinPoint, String nome, Boolean ativo, Pageable page) {
		System.out.println("Before method:" + joinPoint.getSignature());
		System.out.println(
				"Buscando por nome produto = " + nome);
	}
}
