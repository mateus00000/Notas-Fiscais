package com.example.Notas.dto;

public class CategoriaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private ProdutoDTO produtoDTO;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Long id, String nome, String descricao, ProdutoDTO produtoDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.produtoDTO = produtoDTO;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public void setProdutoDTO(ProdutoDTO produtoDTO) {
        this.produtoDTO = produtoDTO;
    }
    
}
