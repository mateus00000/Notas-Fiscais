package com.example.Notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notas.entities.Produto;
import com.example.Notas.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> obterTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> obterProdutosPorId(Long id){
        return produtoRepository.findById(id);
    }
    public Produto atualizarProduto(Long id, Produto produto){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isPresent()){
            Produto c = produtoExistente.get();
            c.setNome(produto.getNome());
            c.setValor(produto.getValor());
            return produtoRepository.save(c);
        } else {
            return null;
        }
    }

    public boolean deletarProduto(Long id) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isPresent()){
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
