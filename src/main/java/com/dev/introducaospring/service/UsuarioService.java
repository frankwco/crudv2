package com.dev.introducaospring.service;


import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.introducaospring.domain.Aluno;
import com.dev.introducaospring.domain.Permissao;
import com.dev.introducaospring.domain.PermissaoUsuario;
import com.dev.introducaospring.domain.Usuario;
import com.dev.introducaospring.dto.request.AlunoDTORequest;
import com.dev.introducaospring.exception.BadResourceException;
import com.dev.introducaospring.exception.ResourceAlreadyExistsException;
import com.dev.introducaospring.exception.ResourceNotFoundException;
import com.dev.introducaospring.repository.AlunoRepository;
import com.dev.introducaospring.repository.PermissaoRepository;
import com.dev.introducaospring.repository.PermissaoUsuarioRepository;
import com.dev.introducaospring.repository.UsuarioRepository;
 
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PermissaoRepository permissaoRepository;
    
    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;
    
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
    	List<Permissao> permissoes = permissaoRepository.buscarPermissaoNome("funcionario"); 
    	Usuario usuarioNovo = usuarioRepository.save(usuario);
    		if(permissoes.size()>0) {
    			PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
    			permissaoUsuario.setPermissao(permissoes.get(0));
    			permissaoUsuario.setUsuario(usuarioNovo);
    			permissaoUsuarioRepository.save(permissaoUsuario);
    		}
            return usuarioNovo;
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