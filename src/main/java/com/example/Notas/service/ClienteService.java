package com.example.Notas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.repository.ClienteRepository;
import com.example.Notas.util.ClienteMapper;
import com.example.Notas.util.Hashing;
import com.example.Notas.util.ValidaEmail;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity <ClienteDTO> criarCliente(ClienteDTO clienteDTO, HttpServletResponse response){
       
        if (!ValidaEmail.validarCaracterArroba(clienteDTO.getEmail())){
            return ResponseEntity.status(422).build();
        }

        Cliente cliente = ClienteMapper.toEntity(clienteDTO); 
        ClienteDTO clienteSalvoDTO = ClienteMapper.toDTO(clienteRepository.save(cliente));

        try {
            String idClient = Hashing.hash(clienteDTO.getId().toString());
            Cookie sessionCookie = new Cookie("idClient", idClient);

            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true);
            sessionCookie.setMaxAge(60 * 60);
            sessionCookie.setPath("/");

            response.addCookie(sessionCookie);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(clienteSalvoDTO);
    }

    public List<ClienteDTO> obterTodosClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTOs = new ArrayList<>();
        for(Cliente cliente : clientes){
            clientesDTOs.add(ClienteMapper.toDTO(cliente));
        }    
        return clientesDTOs;
    }

    public Optional<ClienteDTO> obterClientePorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return Optional.of(ClienteMapper.toDTO(cliente.get()));
        } else {
            return Optional.empty();
        }
    }
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = ClienteMapper.toEntity(clienteDTO);
            cliente.setId(id);
            cliente = clienteRepository.save(cliente);
            return ClienteMapper.toDTO(cliente);
        } else {
            return null;
        }
    }

    public boolean deletarCliente(Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if(clienteExistente.isPresent()){
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
