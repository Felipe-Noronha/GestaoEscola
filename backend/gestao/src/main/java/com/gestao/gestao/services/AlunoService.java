package com.gestao.gestao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.gestao.models.Aluno;
import com.gestao.gestao.repositories.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id){
        return alunoRepository.findById(id);
    }

    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public void deleteById(Long id){
        alunoRepository.deleteById(id);
    }
}
