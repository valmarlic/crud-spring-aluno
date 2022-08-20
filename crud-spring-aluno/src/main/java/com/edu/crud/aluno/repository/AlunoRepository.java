package com.edu.crud.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.crud.aluno.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
