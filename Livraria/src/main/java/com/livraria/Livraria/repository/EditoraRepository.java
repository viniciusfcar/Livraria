package com.livraria.Livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository <Editora, Long>{

}
