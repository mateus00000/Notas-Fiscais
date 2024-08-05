package com.example.Notas.util;

import com.example.Notas.dto.ClienteDTO;
import com.example.Notas.dto.EnderecoDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.entities.Endereco;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente){

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(cliente.getEndereco().getId());
        enderecoDTO.setCep(cliente.getEndereco().getCep());
        enderecoDTO.setNumero(cliente.getEndereco().getNumero());
        enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), enderecoDTO);
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
        return cliente;
    }
}
