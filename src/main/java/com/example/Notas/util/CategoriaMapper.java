package com.example.Notas.util;

import com.example.Notas.dto.CategoriaDTO;
import com.example.Notas.dto.ProdutoDTO;
import com.example.Notas.entities.Categoria;
import com.example.Notas.entities.Produto;

public class CategoriaMapper {
    public static CategoriaDTO toDTO(Categoria categoria){

        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(categoria.getProduto().getId());
        produtoDTO.setNome(categoria.getProduto().getNome());
        produtoDTO.setValor(categoria.getProduto().getValor());
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao(), produtoDTO);
    }

    public static Categoria toEntity(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        if (categoriaDTO.getProdutoDTO() != null){
            Produto produto = new Produto();
            produto.setId(categoriaDTO.getProdutoDTO().getId());
            produto.setNome(categoriaDTO.getProdutoDTO().getNome());
            produto.setValor(categoriaDTO.getProdutoDTO().getValor());
            categoria.setProduto(produto);
        }
        return categoria;
    }
}
    
