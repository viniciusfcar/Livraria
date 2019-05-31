package com.livraria.Livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Autor;
import com.livraria.Livraria.models.Livro;
import com.livraria.Livraria.repository.AutorRepository;
import com.livraria.Livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private AutorService service;
	
	
	public List<Livro> findAll() {
		return repository.findAll();
	}
	
	public Livro findOne(long id) {
		return repository.getOne(id);
	}
	
	public void save(Livro livro) {
		repository.saveAndFlush(livro);
	}
	
	public void update(long id) {
		
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public List<Autor> arrayAutores(long id) {
		Livro l = repository.findById(id).get();
		return l.getAutores();
	}
}
