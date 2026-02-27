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

import com.senai.smartfila.entities.Aluno;
import com.senai.smartfila.services.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin("*")
public class AlunoController {
	@Autowired
	private AlunoService service;

	@GetMapping
	public ResponseEntity<List<Aluno>> listar() {
		return ResponseEntity.ok(service.listarTodos());
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
		Optional<Aluno> aluno = service.buscarPorId(id);
		
		if(aluno != null) {
			return ResponseEntity.ok(aluno.get());
		}
		return ResponseEntity.notFound().build();	}

	@PostMapping
	public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno) {
		Aluno novoAluno = service.salvar(aluno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
		Aluno alunoAtualizado = service.atualizar(id, aluno);
		
		if(alunoAtualizado != null) {
			return ResponseEntity.ok(alunoAtualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Optional<Aluno> aluno = service.buscarPorId(id);
			
		if(aluno.isPresent()) {
			
			service.deletar(id);

			return ResponseEntity.status(HttpStatus.OK)
					.body("Sucesso: O aluno foi excluido permanentemente");
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Erro: Não foi possivel deletar. o Aluno com ID " + id + " não foi encontrado.");
		
	}
	
}
