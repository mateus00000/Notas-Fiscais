package com.example.Notas.util;

import com.example.Notas.dto.CategoriaDTO;
import com.example.Notas.entities.Categoria;

public class CategoriaMapper {
    public static CategoriaDTO toDTO(Categoria categoria){
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public static Categoria toEntity(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        return categoria;
    }
}
    
