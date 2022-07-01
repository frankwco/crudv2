package com.dev.crudv2.controller;


import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.crudv2.domain.Permissao;

import com.dev.crudv2.service.PermissaoService;

 
@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {
   
    
    @Autowired
    private PermissaoService permissaoService;
    
    
    @GetMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Permissao>> findAll(Pageable pageable) {           	
            return ResponseEntity.ok(permissaoService.findAll(pageable));        
    }
 

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Permissao> findById(@PathVariable long id) {    	
        	Permissao permissao = permissaoService.findById(id);
            return ResponseEntity.ok(permissao);        
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<Permissao> add(@RequestBody Permissao permissao) 
            throws URISyntaxException {        
        	Permissao usuarioNovo = permissaoService.save(permissao);
            return ResponseEntity.created(new URI("/api/permissao/" + usuarioNovo.getId()))
                    .body(usuarioNovo);      
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Permissao> update(@Valid @RequestBody Permissao permissao, 
            @PathVariable long id) {       
            permissao.setId(id);
            permissaoService.update(permissao);
            return ResponseEntity.ok().build();       
    }    
  
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {        
            permissaoService.deleteById(id);
            return ResponseEntity.ok().build();       
    }
}