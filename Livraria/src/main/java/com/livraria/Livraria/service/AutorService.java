package com.livraria.Livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Autor;
import com.livraria.Livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repository;
	
	public List<Autor> findAll() {
		return repository.findAll();
	}
	
	public Autor findOne(long id) {
		return repository.getOne(id);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void save(Autor autor) {
		repository.saveAndFlush(autor);
	}
	
	public void update(long id) {
		
	}
}
