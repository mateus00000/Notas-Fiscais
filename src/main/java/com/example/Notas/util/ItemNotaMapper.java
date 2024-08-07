package com.example.Notas.util;

import com.example.Notas.dto.ItemNotaDTO;
import com.example.Notas.entities.ItemNota;
import com.example.Notas.entities.Nota;
import com.example.Notas.entities.Produto;

public class ItemNotaMapper {
    public static ItemNotaDTO toDTO(ItemNota itemNota) {
        return new ItemNotaDTO(
            itemNota.getId(),
            itemNota.getNota().getId(),
            itemNota.getProduto().getId(),
            itemNota.getPreco(),
            itemNota.getQuantidade()
        );
    }

    public static ItemNota toEntity(ItemNotaDTO itemNotaDTO, Nota nota, Produto produto) {
        ItemNota itemNota = new ItemNota();
        itemNota.setId(itemNotaDTO.getId());
        itemNota.setNota(nota);
        itemNota.setProduto(produto);
        itemNota.setPreco(itemNotaDTO.getPreco());
        itemNota.setQuantidade(itemNotaDTO.getQuantidade());
        return itemNota;
    }
}
