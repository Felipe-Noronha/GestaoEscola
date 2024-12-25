package com.gestao.gestao.controllers;

import com.gestao.gestao.models.Nota;
import com.gestao.gestao.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    // Consulta as notas de um aluno específico
    @GetMapping("/aluno/{alunoId}")
    public List<Nota> getNotasByAluno(@PathVariable Long alunoId) {
        return notaService.findByAlunoId(alunoId);  // Método que filtra notas do aluno
    }

    // Consulta a nota de um aluno específico em uma determinada turma
    @GetMapping("/aluno/{alunoId}/turma/{turmaId}")
    public ResponseEntity<Nota> getNotaByAlunoAndTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
        Nota nota = notaService.findByAlunoAndTurma(alunoId, turmaId);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        }
        return ResponseEntity.notFound().build();
    }

    // Cadastro ou atualização de uma nota
    @PostMapping
    public Nota createOrUpdateNota(@RequestBody Nota nota) {
        return notaService.save(nota);
    }

    // Exclui uma nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
