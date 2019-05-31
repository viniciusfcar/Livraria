package com.livraria.Livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Role;
import com.livraria.Livraria.repository.RoleRepository;



@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}
}
