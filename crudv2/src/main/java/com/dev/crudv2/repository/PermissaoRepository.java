package com.dev.crudv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crudv2.domain.Permissao;
 
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	
}