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

import com.dev.crudv2.domain.Produto;

import com.dev.crudv2.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Produto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(produtoService.findAll(pageable));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok(produto);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Produto> add(@RequestBody Produto produto) 
			throws URISyntaxException {
		Produto usuarioNovo = produtoService.save(produto);
		return ResponseEntity.created(new URI("/api/produto/" + 
				usuarioNovo.getId())).body(usuarioNovo);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto, @PathVariable long id) {
		produto.setId(id);
		produtoService.update(produto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		produtoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}