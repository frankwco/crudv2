package com.dev.introducaospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.introducaospring.domain.PermissaoUsuario;
 
public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
	
	
}