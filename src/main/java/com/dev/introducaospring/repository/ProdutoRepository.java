package com.dev.introducaospring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.introducaospring.domain.Produto;
 
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//buscar todos os produtos de acordo com o id da categoria
	@Query("select p from Produto p where p.categoria.id=?1")
	//@Query(nativeQuery = true, value="")
	public List<Produto> buscarProdutosCategoria(Long idCategoria);	
	
	@Query("select p from Produto p where p.categoria.nome like %?1%")
	public List<Produto> buscarProdutosNomeCategoria(Long nomeCategoria);	
}