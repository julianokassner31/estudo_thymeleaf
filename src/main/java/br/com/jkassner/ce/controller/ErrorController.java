package br.com.jkassner.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@GetMapping("/403")
	public ModelAndView error() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("403.html");
		
		return modelAndView;
	}
}
