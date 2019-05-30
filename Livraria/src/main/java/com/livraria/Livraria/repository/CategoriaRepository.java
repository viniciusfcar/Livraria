package com.livraria.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

}
