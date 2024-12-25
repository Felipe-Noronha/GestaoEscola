package com.gestao.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.gestao.models.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
}
