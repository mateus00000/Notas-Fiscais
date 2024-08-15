package com.example.Notas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Notas.entities.ClienteAuth;

public class ClienteAuthRepository extends JpaRepository <ClienteAuth, Long>{
    ClienteAuth findByUsername(String username);
}
