package com.livraria.Livraria.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.Livraria.models.Livro;
import com.livraria.Livraria.service.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@GetMapping("/listar")
	public List<Livro> litar() {
		return service.findAll();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Livro> buscar(@PathVariable long id) {
		Livro l = service.findOne(id);
		
		if(l == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(l);
	}
}
