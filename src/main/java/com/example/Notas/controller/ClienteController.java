package com.example.Notas.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.criarCliente(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> obterTodosClientes() {
        
        return clienteService.obterTodosClientes();
    }

    @GetMapping("/{id}")
    public Optional<ClienteDTO> obterClientePorId(@PathVariable Long id){

        return clienteService.obterClientePorId(id);
    }

    @PutMapping("/{id}")
    public Optional<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        
        return Optional.ofNullable(clienteService.atualizarCliente(id, clienteDTO));
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public Optional deletarCliente(@PathVariable Long id) {
        return Optional.ofNullable(clienteService.deletarCliente(id));
        /*if (deletado){
            return ResponseEntity.noContent().build(); //Retorna 204 se a exclusão for bem sicedida
        }else{
            return ResponseEntity.notFound().build(); //Retorna 404 se o cliente não for encontrado
        }*/
    }
}