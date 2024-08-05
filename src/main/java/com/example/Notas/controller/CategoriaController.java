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

import com.example.Notas.dto.CategoriaDTO;
import com.example.Notas.entities.Categoria;
import com.example.Notas.service.CategoriaService;
import com.example.Notas.util.CategoriaMapper;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public CategoriaDTO criarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
        Categoria categoriaCriada = categoriaService.criarCategoria(categoria);
        return CategoriaMapper.toDTO(categoriaCriada);
    }

    @GetMapping
    public List<CategoriaDTO> obterTodasCategorias() {
        List<Categoria> categorias = categoriaService.obterTodasCategorias();
        List<CategoriaDTO> categoriasDTOs = new ArrayList<>();
        for(Categoria categoria : categorias){
            categoriasDTOs.add(CategoriaMapper.toDTO(categoria));
        }
        return categoriasDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obterCategoriaPorId(@PathVariable Long id){
        return categoriaService.obterCategoriaPorId(id)
            .map(categoria -> ResponseEntity.ok(CategoriaMapper.toDTO(categoria)))
            .orElse(ResponseEntity.notFound().build()); //Retorna erro 404 se não encontrado
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        System.out.println("PASSOU");
        Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
        Categoria categoriaAtualizado = categoriaService.atualizarCategoria(id, categoria);
        if (categoriaAtualizado != null){
            return ResponseEntity.ok(CategoriaMapper.toDTO(categoriaAtualizado));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        boolean deletado = categoriaService.deletarCategoria(id);
        if (deletado){
            return ResponseEntity.noContent().build(); //Retorna 204 se a exclusão for bem sicedida
        }else{
            return ResponseEntity.notFound().build(); //Retorna 404 se o categoria não for encontrado
        }
    }
}
