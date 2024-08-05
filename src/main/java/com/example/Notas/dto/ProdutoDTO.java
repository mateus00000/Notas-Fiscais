package com.example.Notas.dto;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double valor;
    private CategoriaDTO categoriaDTO;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, Double valor, CategoriaDTO categoriaDTO) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoriaDTO = categoriaDTO;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CategoriaDTO getCategoriaDTO() {
        return categoriaDTO;
    }

    public void setCategoriaDTO(CategoriaDTO categoriaDTO){
        this.categoriaDTO = categoriaDTO;
    }
}
