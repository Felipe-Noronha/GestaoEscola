package com.gestao.gestao.repositories;

import com.gestao.gestao.models.Nota;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoId(Long alunoId);

    Nota findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}
