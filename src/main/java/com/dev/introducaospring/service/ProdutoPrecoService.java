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
import com.dev.introducaospring.domain.Produto;
import com.dev.introducaospring.domain.ProdutoPreco;
import com.dev.introducaospring.dto.request.AlunoDTORequest;
import com.dev.introducaospring.exception.BadResourceException;
import com.dev.introducaospring.exception.ResourceAlreadyExistsException;
import com.dev.introducaospring.exception.ResourceNotFoundException;
import com.dev.introducaospring.repository.AlunoRepository;
import com.dev.introducaospring.repository.PermissaoRepository;
import com.dev.introducaospring.repository.PermissaoUsuarioRepository;
import com.dev.introducaospring.repository.ProdutoPrecoRepository;
import com.dev.introducaospring.repository.ProdutoRepository;
 
@Service
public class ProdutoPrecoService {
    
    @Autowired
    private ProdutoPrecoRepository produtoPrecoRepository;

     public ProdutoPreco save(ProdutoPreco produtoPreco)  {   	
            return produtoPrecoRepository.save(produtoPreco);
    }
  
}