package com.dev.crudv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crudv2.domain.Usuario;
 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
}