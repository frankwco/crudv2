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

import com.dev.crudv2.domain.Usuario;

import com.dev.crudv2.service.UsuarioService;

 
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
   
    
    @Autowired
    private UsuarioService usuarioService;
    
    
    @GetMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Usuario>> findAll(Pageable pageable) {           	
            return ResponseEntity.ok(usuarioService.findAll(pageable));        
    }
 

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findById(@PathVariable long id) {    	
        	Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);        
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<Usuario> add(@RequestBody Usuario usuario) 
            throws URISyntaxException {        
        	Usuario usuarioNovo = usuarioService.save(usuario);
            return ResponseEntity.created(new URI("/api/usuario/" + usuarioNovo.getId()))
                    .body(usuarioNovo);      
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario, 
            @PathVariable long id) {       
            usuario.setId(id);
            usuarioService.update(usuario);
            return ResponseEntity.ok().build();       
    }    
  
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {        
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();       
    }
}