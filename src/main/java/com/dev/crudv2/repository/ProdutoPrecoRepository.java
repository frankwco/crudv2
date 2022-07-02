package com.dev.crudv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crudv2.domain.ProdutoPreco;
 
public interface ProdutoPrecoRepository extends JpaRepository<ProdutoPreco, Long> {
	
	
}