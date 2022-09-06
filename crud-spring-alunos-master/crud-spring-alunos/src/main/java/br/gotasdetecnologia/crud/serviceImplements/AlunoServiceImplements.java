package br.gotasdetecnologia.crud.serviceImplements;

import java.util.List;
import org.springframework.stereotype.Service;
import br.gotasdetecnologia.crud.entity.Aluno;
import br.gotasdetecnologia.crud.repository.AlunoRepository;
import br.gotasdetecnologia.crud.service.AlunoService;

@Service
public class AlunoServiceImplements implements AlunoService {
	
	
	private AlunoRepository alunoRepository;
	
	
	
	public AlunoServiceImplements(AlunoRepository alunoRepository) {
		super();
		this.alunoRepository = alunoRepository;
	}


	@Override
	public List<Aluno> getAllAlunos(){
		return alunoRepository.findAll();
			
		}


	@Override
	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}


	@Override
	public Aluno getAlunoById(Long id) {
		return alunoRepository.findById(id).get();
	}


	@Override
	public Aluno atualizarAluno(Aluno aluno) {
		
		return alunoRepository.save(aluno);
		
	}


	@Override
	public void excluirAlunoById(Long id) {
		alunoRepository.deleteById(id);
		
	}


}


