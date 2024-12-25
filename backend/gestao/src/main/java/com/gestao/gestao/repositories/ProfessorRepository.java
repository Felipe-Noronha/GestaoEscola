package com.gestao.gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.gestao.models.Professor;

public interface ProfessorRepository extends JpaRepository <Professor, Long> {
    
}
