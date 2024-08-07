package com.example.Notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notas.entities.ItemNota;
import com.example.Notas.repository.ItemNotaRepository;
import com.example.Notas.repository.NotaRepository;
import com.example.Notas.repository.ProdutoRepository;

@Service
public class ItemNotaService {
    @Autowired
    private ItemNotaRepository itemNotaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemNota criarItemNota(ItemNota itemNota) {
        return itemNotaRepository.save(itemNota);
    }

    public List<ItemNota> obterTodosItensNota() {
        return itemNotaRepository.findAll();
    }

    public Optional<ItemNota> obterItemNotaPorId(Long id) {
        return itemNotaRepository.findById(id);
    }

    public ItemNota atualizarItemNota(Long id, ItemNota itemNota) {
        Optional<ItemNota> itemNotaExistente = itemNotaRepository.findById(id);
        if (itemNotaExistente.isPresent()) {
            ItemNota in = itemNotaExistente.get();
            in.setNota(itemNota.getNota());
            in.setProduto(itemNota.getProduto());
            in.setPreco(itemNota.getPreco());
            in.setQuantidade(itemNota.getQuantidade());
            return itemNotaRepository.save(in);
        } else {
            return null;
        }
    }

    public boolean deletarItemNota(Long id) {
        Optional<ItemNota> itemNotaExistente = itemNotaRepository.findById(id);
        if (itemNotaExistente.isPresent()) {
            itemNotaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
