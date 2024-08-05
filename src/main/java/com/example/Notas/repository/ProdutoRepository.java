package com.example.Notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notas.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    
}
