package com.livraria.Livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.Livraria.models.Autor;
import com.livraria.Livraria.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@GetMapping("/lista_autores")
	public ModelAndView listaAutores() {
		ModelAndView mv = new ModelAndView("autor/lista_autores");
		mv.addObject("autores", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar_autor")
	public ModelAndView save(@Valid Autor autor, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastroAutor(autor);
	    }
		
		service.save(autor);
		return listaAutores();
	}
	
	@RequestMapping("/cadastro_autor")
	public ModelAndView cadastroAutor(Autor autor) {
		ModelAndView mv = new ModelAndView("autor/cadastro_autor");
		mv.addObject("autor", autor);
		return mv;
	}
	
	@GetMapping("/delete_autor/{id}")
	public ModelAndView deleteLivro(@PathVariable("id") long id) {
		service.delete(id);
		return listaAutores();
	}
	
	@GetMapping("/alterar_autor/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Autor autor = service.findOne(id);
		return cadastroAutor(autor);
	}
}
