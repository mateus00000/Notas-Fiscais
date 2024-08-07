package com.example.Notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notas.entities.Nota;
import com.example.Notas.repository.NotaRepository;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public Nota criarNota(Nota nota){
        return notaRepository.save(nota);
    }

    public List<Nota> obterTodasNotas(){
        return notaRepository.findAll();
    }

    public Optional<Nota> obterNotaPorId(Long id){
        return notaRepository.findById(id);
    }

    public Nota atualizarNota(Long id, Nota nota){
        Optional<Nota> notaExistente = notaRepository.findById(id);
        if(notaExistente.isPresent()){
            Nota n = notaExistente.get();
            n.setCliente(nota.getCliente());
            n.setProduto(nota.getProduto());
            n.setData(nota.getData());
            return notaRepository.save(n);
        } else {
            return null;
        }
    }

    public boolean deletarNota(Long id) {
        Optional<Nota> notaExistente = notaRepository.findById(id);
        if(notaExistente.isPresent()){
            notaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
