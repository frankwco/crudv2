package com.dev.crudv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crudv2.domain.Produto;
 
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	
}