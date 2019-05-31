package com.livraria.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
	
	Administrador findByUsername(String login);
}
