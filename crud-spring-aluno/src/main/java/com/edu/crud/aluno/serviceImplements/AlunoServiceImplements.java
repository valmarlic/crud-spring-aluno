package com.edu.crud.aluno.serviceImplements;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.crud.aluno.entity.Aluno;
import com.edu.crud.aluno.service.AlunoService;

@Service
public class AlunoServiceImplements implements AlunoService{
	private Aluno alunoRepository;
	
	
	
	
	public AlunoServiceImplements(Aluno alunoRepository) {
		super();
		this.alunoRepository = alunoRepository;
	}



	@Override
	public List<Aluno> getAllAlunos(){
		//criado atravez da ide STL
		return alunoRepository.findAll();
	}



	@Override
	public Aluno salvarAluno(Aluno aluno) {
		// TODO Auto-generated method stub
		return alunoRepository.save(aluno); 
	}



	@Override
	public Aluno getAlunoById(Long id) {
		// TODO Auto-generated method stub
		return (Aluno) alunoRepository.findById(id); 
	}
	
}
