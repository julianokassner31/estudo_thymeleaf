package br.com.jkassner.ce.config;

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
}
