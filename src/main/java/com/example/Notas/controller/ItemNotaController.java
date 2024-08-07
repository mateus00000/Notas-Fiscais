package com.example.Notas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<ItemNotaDTO> obterItemNotaPorId(@PathVariable Long id) {
        return itemNotaService.obterItemNotaPorId(id)
            .map(itemNota -> ResponseEntity.ok(ItemNotaMapper.toDTO(itemNota)))
            .orElse(ResponseEntity.notFound().build()); // Retorna erro 404 se não encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemNotaDTO> atualizarItemNota(@PathVariable Long id, @RequestBody ItemNotaDTO itemNotaDTO) {
        Nota nota = notaService.obterNotaPorId(itemNotaDTO.getNotaId()).orElseThrow(() -> new RuntimeException("Nota não encontrada"));
        Produto produto = produtoService.obterProdutosPorId(itemNotaDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        ItemNota itemNota = ItemNotaMapper.toEntity(itemNotaDTO, nota, produto);
        ItemNota itemNotaAtualizado = itemNotaService.atualizarItemNota(id, itemNota);
        if (itemNotaAtualizado != null) {
            return ResponseEntity.ok(ItemNotaMapper.toDTO(itemNotaAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemNota(@PathVariable Long id) {
        boolean deletado = itemNotaService.deletarItemNota(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 se a exclusão for bem-sucedida
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se o itemNota não for encontrado
        }
    }
}
