package com.senai.smartfila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.smartfila.entities.Documentos;
import com.senai.smartfila.repositories.DocumentosRepository;

@Service
public class DocumentosService {
	
	@Autowired
	private DocumentosRepository repository;
	
	public List<Documentos> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<Documentos> buscarPorId(Long id) {
		return repository.findById(id);
		
	}
	
	public Documentos salvar(Documentos documentos) {
		return repository.save(documentos);
	}
	
	public Documentos atualizar(Long id, Documentos documentosAlterados) {
		Optional<Documentos> documentoExistente = buscarPorId(id);
		
		if(documentoExistente.isPresent()) {
			Documentos existente = documentoExistente.get();
			existente.setCpf(documentosAlterados.getCpf());
			existente.setRg(documentosAlterados.getRg());
			repository.save(existente);
		}
		return null;
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}

}
