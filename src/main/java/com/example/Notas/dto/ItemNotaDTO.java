package com.example.Notas.dto;

public class ItemNotaDTO {
    private Long id;
    private Long notaId;
    private Long produtoId;
    private Double preco;
    private Integer quantidade;

    public ItemNotaDTO() {}

    public ItemNotaDTO(Long id, Long notaId, Long produtoId, Double preco, Integer quantidade) {
        this.id = id;
        this.notaId = notaId;
        this.produtoId = produtoId;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotaId() {
        return notaId;
    }

    public void setNotaId(Long notaId) {
        this.notaId = notaId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
