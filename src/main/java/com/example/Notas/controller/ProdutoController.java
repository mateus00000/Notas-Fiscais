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

import com.example.Notas.dto.ProdutoDTO;
import com.example.Notas.entities.Produto;
import com.example.Notas.service.ProdutoService;
import com.example.Notas.util.ProdutoMapper;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoDTO criarProduto(@RequestBody ProdutoDTO produtoDTO){
        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        Produto produtoCriado = produtoService.criarProduto(produto);
        return ProdutoMapper.toDTO(produtoCriado);
    }

    @GetMapping
    public List<ProdutoDTO> obterTodosProdutos() {
        List<Produto> produtos = produtoService.obterTodosProdutos();
        List<ProdutoDTO> produtosDTOs = new ArrayList<>();
        for(Produto produto : produtos){
            produtosDTOs.add(ProdutoMapper.toDTO(produto));
        }
        return produtosDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterProdutosPorId(@PathVariable Long id){
        return produtoService.obterProdutosPorId(id)
            .map(produto -> ResponseEntity.ok(ProdutoMapper.toDTO(produto)))
            .orElse(ResponseEntity.notFound().build()); //Retorna erro 404 se não encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        System.out.println("PASSOU");
        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado != null){
            return ResponseEntity.ok(ProdutoMapper.toDTO(produtoAtualizado));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        boolean deletado = produtoService.deletarProduto(id);
        if (deletado){
            return ResponseEntity.noContent().build(); //Retorna 204 se a exclusão for bem sicedida
        }else{
            return ResponseEntity.notFound().build(); //Retorna 404 se o produto não for encontrado
        }
    }

}
