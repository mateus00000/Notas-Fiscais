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

import com.example.Notas.dto.NotaDTO;
import com.example.Notas.entities.Cliente;
import com.example.Notas.entities.Nota;
import com.example.Notas.entities.Produto;
import com.example.Notas.service.ClienteService;
import com.example.Notas.service.NotaService;
import com.example.Notas.service.ProdutoService;
import com.example.Notas.util.NotaMapper;

@RestController
@RequestMapping("/notas")
public class NotaController {
    
    @Autowired
    private NotaService notaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public NotaDTO criarNota(@RequestBody NotaDTO notaDTO){
        Cliente cliente = clienteService.obterClientePorId(notaDTO.getClienteId()).orElse(null);
        Produto produto = produtoService.obterProdutosPorId(notaDTO.getProdutoId()).orElse(null);
        Nota nota = NotaMapper.toEntity(notaDTO, cliente, produto);
        Nota notaCriada = notaService.criarNota(nota);
        return NotaMapper.toDTO(notaCriada);
    }

    @GetMapping
    public List<NotaDTO> obterTodasNotas() {
        List<Nota> notas = notaService.obterTodasNotas();
        List<NotaDTO> notasDTOs = new ArrayList<>();
        for(Nota nota : notas){
            notasDTOs.add(NotaMapper.toDTO(nota));
        }
        return notasDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> obterNotaPorId(@PathVariable Long id){
        return notaService.obterNotaPorId(id)
            .map(nota -> ResponseEntity.ok(NotaMapper.toDTO(nota)))
            .orElse(ResponseEntity.notFound().build()); //Retorna erro 404 se não encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> atualizarNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO){
        Cliente cliente = clienteService.obterClientePorId(notaDTO.getClienteId()).orElse(null);
        Produto produto = produtoService.obterProdutosPorId(notaDTO.getProdutoId()).orElse(null);
        Nota nota = NotaMapper.toEntity(notaDTO, cliente, produto);
        Nota notaAtualizada = notaService.atualizarNota(id, nota);
        if (notaAtualizada != null){
            return ResponseEntity.ok(NotaMapper.toDTO(notaAtualizada));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id){
        boolean deletado = notaService.deletarNota(id);
        if (deletado){
            return ResponseEntity.noContent().build(); //Retorna 204 se a exclusão for bem sicedida
        }else{
            return ResponseEntity.notFound().build(); //Retorna 404 se o nota não for encontrado
        }
    }
}
