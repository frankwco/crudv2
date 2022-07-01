package com.dev.crudv2.service;


import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.crudv2.domain.Aluno;
import com.dev.crudv2.domain.Usuario;
import com.dev.crudv2.dto.request.AlunoDTORequest;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceAlreadyExistsException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.repository.AlunoRepository;
import com.dev.crudv2.repository.UsuarioRepository;
 
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }
    
    public Usuario findById(Long id)  {
    	Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }
    
    public Page<Usuario> findAll(Pageable pageable) {
        
        return usuarioRepository.findAll(pageable);
    }
   
    public Usuario save(Usuario usuario)  {
            return usuarioRepository.save(usuario);
    }
    
    public void update(Usuario usuario) {      
    	usuarioRepository.save(usuario);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
        	usuarioRepository.deleteById(id);
        }        
    }
    
    public Long count() {
        return usuarioRepository.count();
    }
}