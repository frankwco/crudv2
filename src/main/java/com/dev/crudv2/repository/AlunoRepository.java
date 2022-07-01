package com.dev.crudv2.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crudv2.domain.Aluno;
 
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Query(value = "select a from Aluno a where a.nome like %?1%")
	Page<Aluno> findByNome(String nome, Pageable page);
	
	
	Page<Aluno> findAll(Pageable page);
}