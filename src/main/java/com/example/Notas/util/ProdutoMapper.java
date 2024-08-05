package com.example.Notas.util;

import com.example.Notas.dto.CategoriaDTO;
import com.example.Notas.dto.ProdutoDTO;
import com.example.Notas.entities.Categoria;
import com.example.Notas.entities.Produto;

public class ProdutoMapper {
    public static ProdutoDTO toDTO(Produto produto){

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(produto.getCategoria().getId());
        categoriaDTO.setNome(produto.getCategoria().getNome());
        categoriaDTO.setDescricao(produto.getCategoria().getDescricao());
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor(), categoriaDTO);
    }

    public static Produto toEntity(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());

        if (produtoDTO.getCategoriaDTO() != null){
            Categoria categoria = new Categoria();
            categoria.setId(produtoDTO.getCategoriaDTO().getId());
            categoria.setNome(produtoDTO.getCategoriaDTO().getNome());
            categoria.setDescricao(produtoDTO.getCategoriaDTO().getDescricao());
            produto.setCategoria(categoria);
        }
        return produto;
    }
}
