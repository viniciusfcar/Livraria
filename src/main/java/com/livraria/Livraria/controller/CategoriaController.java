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
import com.livraria.Livraria.models.Categoria;
import com.livraria.Livraria.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("lista_categorias")
	public ModelAndView listaCategorias() {
		ModelAndView mv = new ModelAndView("categoria/lista_categorias");
		mv.addObject("categorias", service.findAll());
		return mv;
	}
	
	@PostMapping("salvar_categoria")
	public ModelAndView save(@Valid Categoria categoria, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastroCategoria(categoria);
	    }
		
		service.save(categoria);
		return listaCategorias();
	}
	
	@RequestMapping("cadastro_categoria")
	public ModelAndView cadastroCategoria(Categoria categoria) {
		ModelAndView mv = new ModelAndView("categoria/cadastro_categoria");
		mv.addObject("categoria", categoria);
		return mv;
	}
	
	@GetMapping("delete_categoria/{id}")
	public ModelAndView deleteCategoria(@PathVariable("id") long id) {
		service.delete(id);
		return listaCategorias();
	}
	
	@GetMapping("alterar_autor/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Categoria categoria = service.findOne(id);
		return cadastroCategoria(categoria);
	}
}
