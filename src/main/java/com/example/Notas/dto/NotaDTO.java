package com.example.Notas.dto;

import java.time.LocalDate;

public class NotaDTO {
    
    private Long id;
    private Long clienteId;
    private Long produtoId;
    private LocalDate data;

    public NotaDTO() {}

    public NotaDTO(Long id, Long clienteId, Long produtoId, LocalDate data) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
