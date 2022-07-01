package com.dev.crudv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.crudv2.domain.PermissaoUsuario;
 
public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
	
	
}