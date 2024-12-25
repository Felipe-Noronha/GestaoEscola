package com.gestao.gestao.services;

import com.gestao.gestao.models.Nota;
import com.gestao.gestao.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    public void deleteById(Long id) {
        notaRepository.deleteById(id);
    }

    public List<Nota> findByAlunoId(Long alunoId) {
        return notaRepository.findByAlunoId(alunoId);
    }

    public Nota findByAlunoAndTurma(Long alunoId, Long turmaId) {
        return notaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);
    }
}
