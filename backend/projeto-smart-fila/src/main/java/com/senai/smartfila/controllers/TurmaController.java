package com.senai.smartfila.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.smartfila.entities.Turma;
import com.senai.smartfila.services.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turma")
@CrossOrigin("*")
public class TurmaController {
	
	@Autowired
	private TurmaService service;
	
	@GetMapping
	public ResponseEntity<List<Turma>> listar(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscar(@PathVariable Long id) {
		Optional<Turma> turma = service.buscarPorId(id);
		
		if(turma.isPresent()) {
			return ResponseEntity.ok(turma.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Turma> criar(@Valid @RequestBody Turma turma) {
		Turma novaTurma = service.salvar(turma);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
				
				
	}
	
	@PutMapping
	public ResponseEntity<Turma> atualizar(@PathVariable Long id, @Valid @RequestBody Turma turma) {
		Turma turmaAtualizado = service.atualizar(id, turma);
		
		if(turmaAtualizado != null) {
			return ResponseEntity.ok(turmaAtualizado);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Optional<Turma> turma = service.buscarPorId(id);
		if(turma.isPresent()) {
			
			service.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Sucesso: A turma foi excluida permanentemente");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Erro:  Não foi possivel deletar. A turma com ID" + id + " não foi encontrada.");
	}

}
