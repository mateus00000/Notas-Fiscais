package com.example.Notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notas.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
