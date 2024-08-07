package com.example.Notas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Notas.dto.ItemNotaDTO;
import com.example.Notas.entities.ItemNota;
import com.example.Notas.entities.Nota;
import com.example.Notas.entities.Produto;
import com.example.Notas.service.ItemNotaService;
import com.example.Notas.service.NotaService;
import com.example.Notas.service.ProdutoService;
import com.example.Notas.util.ItemNotaMapper;

@RestController
@RequestMapping("/itensNota")
public class ItemNotaController {
    @Autowired
    private ItemNotaService itemNotaService;

    @Autowired
    private NotaService notaService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ItemNotaDTO criarItemNota(@RequestBody ItemNotaDTO itemNotaDTO) {
        Nota nota = notaService.obterNotaPorId(itemNotaDTO.getNotaId()).orElseThrow(() -> new RuntimeException("Nota não encontrada"));
        Produto produto = produtoService.obterProdutosPorId(itemNotaDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        ItemNota itemNota = ItemNotaMapper.toEntity(itemNotaDTO, nota, produto);
        ItemNota itemNotaCriado = itemNotaService.criarItemNota(itemNota);
        return ItemNotaMapper.toDTO(itemNotaCriado);
    }

    @GetMapping
    public List<ItemNotaDTO> obterTodosItensNota() {
        List<ItemNota> itensNota = itemNotaService.obterTodosItensNota();
        List<ItemNotaDTO> itensNotaDTOs = new ArrayList<>();
        for (ItemNota itemNota : itensNota) {
            itensNotaDTOs.add(ItemNotaMapper.toDTO(itemNota));
        }
        return itensNotaDTOs;
    }
}
