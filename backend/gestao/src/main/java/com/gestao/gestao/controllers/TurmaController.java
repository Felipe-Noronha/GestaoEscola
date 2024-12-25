package com.gestao.gestao.controllers;

import com.gestao.gestao.models.Turma;
import com.gestao.gestao.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;


    @GetMapping
    public List<Turma> getAllTurmas() {
        return turmaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Long id) {
        Optional<Turma> turma = turmaService.findById(id);
        return turma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turma createOrUpdateTurma(@RequestBody Turma turma) {
        return turmaService.save(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        turmaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
