package com.senai.smartfila.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Column(unique = true)
    @NotBlank
    private String ra;
    
    @ManyToOne
    @JoinColumn(name = "fk_turma")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Turma turma;
    
    @OneToOne
    @JoinColumn(name = "fk_documentos")
    @JsonManagedReference
    private Documentos documentos;
    
    public Aluno() {}
    public Aluno(String nome, String ra, Turma turma, Documentos documentos) {
    	this.nome = nome;
    	this.ra = ra;
    	this.turma = turma;
    	this.documentos = documentos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public Documentos getDocumentos(){ return documentos; }
    public void setDocumentos(Documentos documentos) {this.documentos = documentos;}
    
}