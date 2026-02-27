package com.senai.smartfila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.smartfila.entities.Aluno;
import com.senai.smartfila.repositories.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;

	public List<Aluno> listarTodos() {
		return repository.findAll();
	}

	public Optional<Aluno> buscarPorId(Long id) {
		return repository.findById(id);
	}

	public Aluno salvar(Aluno aluno) {
		return repository.save(aluno);
	}

	public Aluno atualizar(Long id, Aluno dados) {
		Optional<Aluno> alunoExistente = buscarPorId(id);
		
		if (alunoExistente.isPresent()) {
			
			Aluno existente = alunoExistente.get();
			existente.setNome(dados.getNome());
			existente.setTurma(dados.getTurma());
			existente.setRa(dados.getRa());
			
			if(dados.getDocumentos() != null) {
				
				existente.setDocumentos(dados.getDocumentos());
			}
			return repository.save(existente);
		}
		return null;
		
	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}
}