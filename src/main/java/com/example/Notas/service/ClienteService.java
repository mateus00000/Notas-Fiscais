package com.example.Notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notas.entities.Cliente;
import com.example.Notas.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obterTodosClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obterClientePorId(Long id){
        return clienteRepository.findById(id);
    }
    public Cliente atualizarCliente(Long id, Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if(clienteExistente.isPresent()){
            Cliente c = clienteExistente.get();
            c.setNome(cliente.getNome());
            c.setEmail(cliente.getEmail());
            return clienteRepository.save(c);
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
