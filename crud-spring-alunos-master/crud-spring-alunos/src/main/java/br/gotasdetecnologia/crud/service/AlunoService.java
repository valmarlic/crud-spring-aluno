package br.gotasdetecnologia.crud.service;

import java.util.List;

import br.gotasdetecnologia.crud.entity.Aluno;

public interface AlunoService {
	
	List<Aluno> getAllAlunos();
	
	Aluno salvarAluno(Aluno aluno);
	
	Aluno getAlunoById(Long id);
	Aluno atualizarAluno(Aluno aluno);
	
	void excluirAlunoById(Long id);

}
