package com.dev.crudv2.controller;


import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.crudv2.domain.Aluno;
import com.dev.crudv2.dto.AlunoDTO;
import com.dev.crudv2.dto.request.AlunoDTORequest;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceAlreadyExistsException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
 
@RestController
@RequestMapping("/api")
@Tag(name = "aluno", description = "API de Alunos")
public class AlunoController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
    
    @Autowired
    private AlunoService alunoService;
    
    
    @GetMapping(value = "/aluno",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AlunoDTO>> findAll(
    		@Parameter(description = "Nome para pesquisa",allowEmptyValue = true, required = false,in = ParameterIn.PATH)            
    		@RequestBody(required=false) String nome, 
            @Parameter(description = "Paginação",example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) 
    		Pageable pageable) {    	
        if (StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(new AlunoDTO().
            		converterListaAlunoDTO(alunoService.findAll(pageable)) );
        }
        else {        	
            return ResponseEntity.ok(new AlunoDTO().
            		converterListaAlunoDTO(alunoService.findAllByNome(nome, pageable)));
        }
    }
 
    @Operation(summary = "Busca ID",description ="Buscar um aluno por ID",tags = {"aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado") })
    @GetMapping(value = "/aluno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> findAlunoById(@PathVariable long id) {
    	
        try {
            Aluno aluno = alunoService.findById(id);
            return ResponseEntity.ok(aluno);  
        } catch (ResourceNotFoundException ex) {
        	logger.error(ex.getMessage());           	
        	 throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
        
    }
    
    @PostMapping(value = "/aluno")
    public ResponseEntity<AlunoDTO> addAluno(@RequestBody AlunoDTORequest aluno) 
            throws URISyntaxException {
        try {
            Aluno novoAluno = alunoService.save(aluno);
            //AlunoDTO alunoDTO = new AlunoDTO().converter(novoAluno);
            return ResponseEntity.created(new URI("/api/aluno/" + novoAluno.getId()))
                    .body(new AlunoDTO().converter(novoAluno));
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping(value = "/aluno/{id}")
    public ResponseEntity<Aluno> updateAluno(@Valid @RequestBody Aluno aluno, 
            @PathVariable long id) {
        try {
            aluno.setId(id);
            alunoService.update(aluno);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
  
    @DeleteMapping(path="/aluno/{id}")
    public ResponseEntity<Void> deleteAlunoById(@PathVariable long id) {
        try {
            alunoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}