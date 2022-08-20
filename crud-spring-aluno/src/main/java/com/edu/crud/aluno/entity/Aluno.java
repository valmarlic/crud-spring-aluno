package com.edu.crud.aluno.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aluno")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "sobreNome")
	private String sobreNome;
	@Column(name = "idade")
	private int idade;
	@Column(name = "email")
	private String email;
	
	
	
	public Aluno() {
		super();
	}

	public Aluno(String nome, String sobreNome, int idade, String email) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.idade = idade;
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobreNome() {
		return sobreNome;
	}
	
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Aluno> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno save(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
