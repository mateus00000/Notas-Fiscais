package com.example.Notas.controller;
import java.util.ArrayList;
import java.util.List;
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
import com.example.Notas.entities.Cliente;
import com.example.Notas.service.ClienteService;
import com.example.Notas.util.ClienteMapper;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteDTO criarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return ClienteMapper.toDTO(clienteCriado);
    }

    @GetMapping
    public List<ClienteDTO> obterTodosClientes() {
        List<Cliente> clientes = clienteService.obterTodosClientes();
        List<ClienteDTO> clientesDTOs = new ArrayList<>();
        for(Cliente cliente : clientes){
            clientesDTOs.add(ClienteMapper.toDTO(cliente));
        }
        return clientesDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obterClientePorId(@PathVariable Long id){
        return clienteService.obterClientePorId(id)
            .map(cliente -> ResponseEntity.ok(ClienteMapper.toDTO(cliente)))
            .orElse(ResponseEntity.notFound().build()); //Retorna erro 404 se não encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        System.out.println("PASSOU");
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        if (clienteAtualizado != null){
            return ResponseEntity.ok(ClienteMapper.toDTO(clienteAtualizado));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        boolean deletado = clienteService.deletarCliente(id);
        if (deletado){
            return ResponseEntity.noContent().build(); //Retorna 204 se a exclusão for bem sicedida
        }else{
            return ResponseEntity.notFound().build(); //Retorna 404 se o cliente não for encontrado
        }
    }
}