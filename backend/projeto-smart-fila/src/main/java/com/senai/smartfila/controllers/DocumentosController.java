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

import com.senai.smartfila.entities.Documentos;
import com.senai.smartfila.services.DocumentosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin("*")
public class DocumentosController {
	
	@Autowired
	private DocumentosService service;
	
	@GetMapping
	public ResponseEntity<List<Documentos>> listar(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Documentos> buscar(@PathVariable Long id) {
		Optional<Documentos> documentos = service.buscarPorId(id);
		
		if(documentos.isPresent()) {
			return ResponseEntity.ok(documentos.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Documentos> criar(@Valid @RequestBody Documentos documentos) {
		Documentos novosDocumentos = service.salvar(documentos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(novosDocumentos);
	}
	
	@PutMapping
	public ResponseEntity<Documentos> atualizar(@PathVariable Long id, @Valid @RequestBody Documentos documentos) {
		Documentos documentoAtualizado = service.atualizar(id, documentos);
		
		if(documentoAtualizado != null) {
			return ResponseEntity.ok(documentoAtualizado);
			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		Optional<Documentos> documento = service.buscarPorId(id);
		
		if(documento.isPresent()) {
			
			service.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Sucesso: Os documenos foram excluidos permanentemente.");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Erro: Não foi possivel deletar. o documento com ID" + id + " não foi encontrado.");
	}

}
