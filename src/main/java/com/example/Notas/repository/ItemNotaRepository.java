package com.example.Notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notas.entities.ItemNota;

public interface ItemNotaRepository extends JpaRepository<ItemNota, Long>{
    
}
