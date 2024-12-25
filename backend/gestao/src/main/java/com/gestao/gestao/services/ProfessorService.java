package com.gestao.gestao.services;

import com.gestao.gestao.models.Professor;
import com.gestao.gestao.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    // Buscar todos os professores
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    // Buscar um professor pelo ID
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    // Salvar ou atualizar um professor
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    // Deletar um professor pelo ID
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }
}
