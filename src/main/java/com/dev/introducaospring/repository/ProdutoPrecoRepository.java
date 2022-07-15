package com.dev.introducaospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.introducaospring.domain.ProdutoPreco;
 
public interface ProdutoPrecoRepository extends JpaRepository<ProdutoPreco, Long> {
	
	
}