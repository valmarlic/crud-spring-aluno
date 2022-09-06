package br.gotasdetecnologia.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gotasdetecnologia.crud.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	

}
