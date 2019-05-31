package com.livraria.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
