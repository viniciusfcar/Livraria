package com.livraria.Livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.Livraria.models.Livro;
import com.livraria.Livraria.service.AutorService;
import com.livraria.Livraria.service.LivroService;

@Controller
public class IndexController {
	
	@Autowired
	private LivroService serviceLivro;
	
	@Autowired
	private AutorService serviceAutor;
	
	@RequestMapping("/")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("livros", serviceLivro.findAll());		
		return mv;
	}
}
