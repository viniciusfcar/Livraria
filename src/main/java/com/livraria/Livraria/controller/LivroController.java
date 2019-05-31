package com.livraria.Livraria.controller;

import java.util.List;

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
import com.livraria.Livraria.models.Livro;
import com.livraria.Livraria.service.AutorService;
import com.livraria.Livraria.service.CategoriaService;
import com.livraria.Livraria.service.EditoraService;
import com.livraria.Livraria.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private AutorService servAutor;
	
	@Autowired
	private EditoraService servEditora;
	
	@Autowired
	private CategoriaService servCategoria;
		
	@GetMapping("/lista_livros")
	public ModelAndView listaLivros() {
		ModelAndView mv = new ModelAndView("/livro/lista_livros");
		mv.addObject("livros", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar_livro")
	public ModelAndView save(@Valid Livro livro, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastroLivro(livro);
	    }
		
		service.save(livro);
		return listaLivros();
	}
	
	@RequestMapping("/cadastro_livro")
	public ModelAndView cadastroLivro(Livro livro) {
		ModelAndView mv = new ModelAndView("/livro/cadastro_livro");
		mv.addObject("autores", servAutor.findAll());
		mv.addObject("editoras", servEditora.findAll());
		mv.addObject("categorias", servCategoria.findAll());
		mv.addObject("livro", livro);
		return mv;
	}
	
	@GetMapping("/delete_livro/{id}")
	public ModelAndView deleteLivro(@PathVariable("id") long id) {
		service.delete(id);
		return listaLivros();
	}
	
	@GetMapping("/alterar_livro/{id}")
	public ModelAndView alterar_livro(@PathVariable("id") long id) {
		Livro livro = service.findOne(id);
		return cadastroLivro(livro);
	}
	
	@GetMapping("/detalhes_livro/{id}")
	public ModelAndView detalhes_livro(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("/livro/detalhes_livro");
		Livro livro = service.findOne(id);
		mv.addObject("livro", livro);
		mv.addObject("autores", livro.getAutores());
		
		return mv;
	}
}
