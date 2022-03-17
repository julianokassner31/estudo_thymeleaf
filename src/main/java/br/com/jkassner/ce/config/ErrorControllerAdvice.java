package br.com.jkassner.ce.config;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jkassner.ce.exceptions.ProdutoNotFoundException;

@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProdutoNotFoundException.class)
	public ModelAndView produtoNaoEncontrado() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/produto/404.html");
		return modelAndView;
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView constraintViolationException() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/500.html");
		return modelAndView;
	}
}
