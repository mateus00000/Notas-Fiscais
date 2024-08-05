package com.example.Notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notas.entities.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
   
}
