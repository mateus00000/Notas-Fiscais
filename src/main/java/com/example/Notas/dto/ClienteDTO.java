package com.example.Notas.dto;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private EnderecoDTO enderecoDTO;
    private ClienteAuthDTO clienteAuth;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, String email, EnderecoDTO enderecoDTO, ClienteAuthDTO clienteAuth) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.enderecoDTO = enderecoDTO;
        this.clienteAuth = clienteAuth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }

    public ClienteAuthDTO getClienteAuth() {
        return clienteAuth;
    }

    public void setClienteAuth(ClienteAuthDTO clienteAuth) {
        this.clienteAuth = clienteAuth;
    }

}
