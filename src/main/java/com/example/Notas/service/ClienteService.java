package com.example.Notas.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.entities.ClienteAuth;
import com.example.Notas.repository.ClienteRepository;
import com.example.Notas.util.ClienteMapper;
import com.example.Notas.util.ValidaEmail;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity <ClienteDTO> criarCliente(ClienteDTO clienteDTO){
        if (!ValidaEmail.validarCaracterArroba(ClienteDTO.getEmail())){
            return ResponseEntity.status(422).build();
        }

        Cliente cliente = ClienteMapper.toEntity(clienteDTO);

        ClienteAuth clienteAuth = new ClienteAuth();
        clienteAuth.setUsername(clienteDTO.getEmail());
        try {
            clienteAuth.setPasswordHash(clienteDTO.getClienteAuth().getPasswordHash());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);
        clienteAuth.setId(clienteSalvo.getId());
        clienteAuthRepository.save(clienteAuth);

        ClienteDTO clienteSalvoDTO = ClienteMapper.toDTO(clienteSalvo);
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
