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

import com.livraria.Livraria.models.Editora;
import com.livraria.Livraria.service.EditoraService;

@Controller
@RequestMapping("/editora")
public class EditoraController {
	
	@Autowired
	private EditoraService service;
	
	@GetMapping("/lista_editoras")
	public ModelAndView listaEditoras() {
		ModelAndView mv = new ModelAndView("editora/lista_editoras");
		mv.addObject("editoras", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar_editora")
	public ModelAndView save(Editora editora) {
		
		service.save(editora);
		return listaEditoras();
	}
	
	@RequestMapping("/cadastro_editora")
	public ModelAndView cadastroEditora(Editora editora) {
		ModelAndView mv = new ModelAndView("editora/cadastro_editora");
		mv.addObject("editora", editora);
		return mv;
	}
	
	@GetMapping("/delete_editora/{id}")
	public ModelAndView deleteEditora(@PathVariable("id") long id) {
		service.delete(id);
		return listaEditoras();
	}
	
	@GetMapping("/alterar_editora/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Editora editora = service.findOne(id);
		return cadastroEditora(editora);
	}
}
