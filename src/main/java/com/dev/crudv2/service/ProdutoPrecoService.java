package com.dev.crudv2.service;


import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.crudv2.domain.Aluno;
import com.dev.crudv2.domain.Permissao;
import com.dev.crudv2.domain.PermissaoUsuario;
import com.dev.crudv2.domain.Produto;
import com.dev.crudv2.domain.ProdutoPreco;
import com.dev.crudv2.dto.request.AlunoDTORequest;
import com.dev.crudv2.exception.BadResourceException;
import com.dev.crudv2.exception.ResourceAlreadyExistsException;
import com.dev.crudv2.exception.ResourceNotFoundException;
import com.dev.crudv2.repository.AlunoRepository;
import com.dev.crudv2.repository.PermissaoRepository;
import com.dev.crudv2.repository.PermissaoUsuarioRepository;
import com.dev.crudv2.repository.ProdutoPrecoRepository;
import com.dev.crudv2.repository.ProdutoRepository;
 
@Service
public class ProdutoPrecoService {
    
    @Autowired
    private ProdutoPrecoRepository produtoPrecoRepository;

     public ProdutoPreco save(ProdutoPreco produtoPreco)  {   	
            return produtoPrecoRepository.save(produtoPreco);
    }
  
}