package com.gestao.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.gestao.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
