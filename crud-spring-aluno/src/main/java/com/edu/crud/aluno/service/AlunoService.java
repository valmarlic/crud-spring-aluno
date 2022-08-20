package com.edu.crud.aluno.service;

import java.util.List;

import com.edu.crud.aluno.entity.Aluno;

public interface AlunoService {
	
	List<Aluno> getAllAlunos();

	Aluno salvarAluno(Aluno aluno);

	Aluno getAlunoById(Long id);
	

}
