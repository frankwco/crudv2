package com.dev.introducaospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.introducaospring.domain.Usuario;
 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
}