package com.example.Notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notas.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{
    
}
