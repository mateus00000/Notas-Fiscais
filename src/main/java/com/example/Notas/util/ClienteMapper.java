package com.example.Notas.util;

import java.security.NoSuchAlgorithmException;

import com.example.Notas.dto.ClienteAuthDTO;
import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.dto.EnderecoDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.entities.ClienteAuth;
import com.example.Notas.entities.Endereco;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente){

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(cliente.getEndereco().getId());
        enderecoDTO.setCep(cliente.getEndereco().getCep());
        enderecoDTO.setNumero(cliente.getEndereco().getNumero());
        enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());

        ClienteAuthDTO clienteAuth = new ClienteAuthDTO();
        clienteAuth.setUsername(cliente.getClienteAuth().getUsername());
        clienteAuth.setPasswordHash(cliente.getClienteAuth().getPasswordHash());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), enderecoDTO, clienteAuth);
    }

    public static Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());

        if (clienteDTO.getEnderecoDTO() != null){
            Endereco endereco = new Endereco();
            endereco.setId(clienteDTO.getEnderecoDTO().getId());
            endereco.setCep(clienteDTO.getEnderecoDTO().getCep());
            endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
            endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
            cliente.setEndereco(endereco);
        }

        if(clienteDTO.getClienteAuth().getPasswordHash() != null){
            ClienteAuth clienteAuth = new ClienteAuth();
            clienteAuth.setUsername(clienteDTO.getEmail());
            try {
                clienteAuth.setPasswordHash(clienteDTO.getClienteAuth().getPasswordHash());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            cliente.setClienteAuth(clienteAuth);
        }

        return cliente;
    }
}
