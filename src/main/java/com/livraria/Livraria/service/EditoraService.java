package com.livraria.Livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Editora;
import com.livraria.Livraria.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repository;
	
	public List<Editora> findAll() {
		return repository.findAll();
	}
	
	public Editora findOne(long id) {
		return repository.getOne(id);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void save(Editora editora) {
		repository.saveAndFlush(editora);
	}
	
	public void update(long id) {
		
	}
}
