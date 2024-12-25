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

    // Consulta as faltas de um aluno específico
    @GetMapping("/aluno/{alunoId}")
    public List<Falta> getFaltasByAluno(@PathVariable Long alunoId) {
        return faltaService.findByAlunoId(alunoId);  // Método que filtra faltas do aluno
    }

    // Consulta as faltas de um aluno específico em uma determinada turma
    @GetMapping("/aluno/{alunoId}/turma/{turmaId}")
    public ResponseEntity<Falta> getFaltaByAlunoAndTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
        Falta falta = faltaService.findByAlunoAndTurma(alunoId, turmaId);
        if (falta != null) {
            return ResponseEntity.ok(falta);
        }
        return ResponseEntity.notFound().build();
    }

    // Cadastro ou atualização de uma falta
    @PostMapping
    public Falta createOrUpdateFalta(@RequestBody Falta falta) {
        return faltaService.save(falta);
    }

    // Exclui uma falta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFalta(@PathVariable Long id) {
        faltaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
