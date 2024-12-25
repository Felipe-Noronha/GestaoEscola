package com.gestao.gestao.repositories;

import com.gestao.gestao.models.Falta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaltaRepository extends JpaRepository<Falta, Long> {
    List<Falta> findByAlunoId(Long alunoId);

    Falta findByAlunoIdAndTurmaId(Long alunoId, Long turmaId); 
}
