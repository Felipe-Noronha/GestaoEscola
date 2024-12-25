package com.gestao.gestao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.gestao.models.Turma;
import com.gestao.gestao.repositories.TurmaRepository;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

    public Optional<Turma> findById(Long id){
        return turmaRepository.findById(id);
    }

    public Turma save(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deleteById(Long id){
        turmaRepository.deleteById(id);
    }
}
