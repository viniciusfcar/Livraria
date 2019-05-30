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

import com.livraria.Livraria.models.Administrador;
import com.livraria.Livraria.service.AdministradorService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private AdministradorService service;
	
	@GetMapping("/lista_administradores")
	public ModelAndView listaAdministradores() {
		ModelAndView mv = new ModelAndView("administrador/lista_administradores");
		mv.addObject("administradores", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar_administrador")
	public ModelAndView save(@Valid Administrador administrador, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastroAdministrador(administrador);
	    }
		
		service.save(administrador);
		return listaAdministradores();
	}
	
	@RequestMapping("/cadastro_administrador")
	public ModelAndView cadastroAdministrador(Administrador administrador) {
		ModelAndView mv = new ModelAndView("administrador/cadastro_administrador");
		mv.addObject("administrador", administrador);
		return mv;
	}
	
	@GetMapping("/delete_administrador/{id}")
	public ModelAndView deleteAdministrador(@PathVariable("id") long id) {
		service.delete(id);
		return listaAdministradores();
	}
	
	@GetMapping("/alterar_administrador/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Administrador administrador = service.findOne(id);
		return cadastroAdministrador(administrador);
	}
	
	@GetMapping("/home_adm")
	public ModelAndView home() {
		return new ModelAndView("/administrador/home_adm");
	}
}
