package com.example.Notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notas.entities.Categoria;
import com.example.Notas.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> obterTodasCategorias(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obterCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoria){
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()){
            Categoria c = categoriaExistente.get();
            c.setNome(categoria.getNome());
            c.setDescricao(categoria.getDescricao());
            return categoriaRepository.save(c);
        }else{
            return null;
        }
    }

    public boolean deletarCategoria(Long id){
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()){
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } 
}
