package com.gestao.gestao.services;

import com.gestao.gestao.models.Falta;
import com.gestao.gestao.repositories.FaltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    public List<Falta> findAll() {
        return faltaRepository.findAll();
    }

    public Optional<Falta> findById(Long id) {
        return faltaRepository.findById(id);
    }

    public Falta save(Falta falta) {
        return faltaRepository.save(falta);
    }

    public void deleteById(Long id) {
        faltaRepository.deleteById(id);
    }

    public List<Falta> findByAlunoId(Long alunoId) {
        return faltaRepository.findByAlunoId(alunoId);
    }

    public Falta findByAlunoAndTurma(Long alunoId, Long turmaId) {
        return faltaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);
    }
}
