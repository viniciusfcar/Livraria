package com.livraria.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	Usuario findByUsername(String email);
}
