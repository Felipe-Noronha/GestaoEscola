package com.gestao.gestao.controllers;

import com.gestao.gestao.models.Falta;
import com.gestao.gestao.services.FaltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faltas")
public class FaltaController {

    @Autowired
    private FaltaService faltaService;


    @GetMapping("/aluno/{alunoId}")
    public List<Falta> getFaltasByAluno(@PathVariable Long alunoId) {
        return faltaService.findByAlunoId(alunoId); 
    }

    @GetMapping("/aluno/{alunoId}/turma/{turmaId}")
    public ResponseEntity<Falta> getFaltaByAlunoAndTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
        Falta falta = faltaService.findByAlunoAndTurma(alunoId, turmaId);
        if (falta != null) {
            return ResponseEntity.ok(falta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Falta createOrUpdateFalta(@RequestBody Falta falta) {
        return faltaService.save(falta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFalta(@PathVariable Long id) {
        faltaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
