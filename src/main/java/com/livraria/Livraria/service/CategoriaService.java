package com.livraria.Livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Categoria;
import com.livraria.Livraria.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria findOne(long id) {
		return repository.getOne(id);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void save(Categoria categoria) {
		repository.saveAndFlush(categoria);
	}
	
	public void update(long id) {
		
	}
}
