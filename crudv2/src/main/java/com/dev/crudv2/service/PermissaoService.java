package com.dev.crudv2.service;


import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dev.crudv2.domain.Permissao;

import com.dev.crudv2.exception.ResourceNotFoundException;

import com.dev.crudv2.repository.PermissaoRepository;
 
@Service
public class PermissaoService {
    
    @Autowired
    private PermissaoRepository permissaoRepository;
    
    private boolean existsById(Long id) {
        return permissaoRepository.existsById(id);
    }
    
    public Permissao findById(Long id)  {
    	Permissao permissao = permissaoRepository.findById(id).orElse(null);
        return permissao;
    }
    
    public Page<Permissao> findAll(Pageable pageable) {
        
        return permissaoRepository.findAll(pageable);
    }
   
    public Permissao save(Permissao permissao)  {
            return permissaoRepository.save(permissao);
    }
    
    public void update(Permissao permissao) {      
    	permissaoRepository.save(permissao);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
        	permissaoRepository.deleteById(id);
        }        
    }
    
    public Long count() {
        return permissaoRepository.count();
    }
}