package com.dev.crudv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.crudv2.domain.Permissao;
 
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	@Query(value = "from Permissao p where p.nome=?1")
	public List<Permissao> buscarPermissaoNome(String nome);
}