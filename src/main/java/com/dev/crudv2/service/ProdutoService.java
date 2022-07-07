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
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoPrecoRepository produtoPrecoRepository;

	private boolean existsById(Long id) {
		return produtoRepository.existsById(id);
	}

	public Produto findById(Long id) {
		Produto produto = produtoRepository.findById(id).orElse(null);
		return produto;
	}

	public Page<Produto> findAll(Pageable pageable) {

		return produtoRepository.findAll(pageable);
	}

	public Produto save(Produto produto) {
		Produto produtoNovo = produtoRepository.save(produto);
		
		ProdutoPreco produtoPreco = new ProdutoPreco();
		produtoPreco.setProduto(produtoNovo);
		produtoPreco.setValorCusto(produtoNovo.getValorCusto());
		produtoPreco.setValorVenda(produtoNovo.getValorVenda());
		produtoPrecoRepository.save(produtoPreco);
		
		return produtoNovo;
	}

	public void update(Produto produto) {
		ProdutoPreco produtoPreco = new ProdutoPreco();
		produtoPreco.setProduto(produto);
		produtoPreco.setValorCusto(produto.getValorCusto());
		produtoPreco.setValorVenda(produto.getValorVenda());
		produtoPrecoRepository.save(produtoPreco);
		
		produtoRepository.save(produto);
	}
	
	
	/**aumento: tipoOperador +, desconto: tipoOperador -**/
	public void atualizarValorProdutoCategoria(Long idCategoria, 
			Double percentual, String tipoOperacao) {
		List<Produto> produtos = produtoRepository.buscarProdutosCategoria(idCategoria);
		for(Produto produto:produtos) {
			if(tipoOperacao.equals("+")) {
				Double porcentagemAumento = 1+(percentual/100);
				Double novoValor = produto.getValorVenda()*porcentagemAumento;
				produto.setValorVenda(novoValor);
			}else {
				produto.setValorVenda(produto.getValorVenda()*(1-(percentual/100)));
			}
			update(produto);
		}
	}

	public void deleteById(Long id) {
		if (!existsById(id)) {
			produtoRepository.deleteById(id);
		}
	}

	public Long count() {
		return produtoRepository.count();
	}
}