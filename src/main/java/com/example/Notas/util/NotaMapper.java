package com.example.Notas.util;

import com.example.Notas.dto.NotaDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.entities.Nota;
import com.example.Notas.entities.Produto;

public class NotaMapper {
    public static NotaDTO toDTO(Nota nota){
        return new NotaDTO(nota.getId(), nota.getCliente().getId(), nota.getProduto().getId(), nota.getData());
    }

    public static Nota toEntity(NotaDTO notaDTO, Cliente cliente, Produto produto){
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setCliente(cliente);
        nota.setProduto(produto);
        nota.setData(notaDTO.getData());
        return nota;
    }
}
