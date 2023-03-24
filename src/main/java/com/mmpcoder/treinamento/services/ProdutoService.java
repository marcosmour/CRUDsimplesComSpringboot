package com.mmpcoder.treinamento.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmpcoder.treinamento.models.Produto;
import com.mmpcoder.treinamento.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto save(Produto produto) {
	
		return produtoRepository.save(produto);
	}

	public List<Produto> finAll() {
		
		return produtoRepository.findAll();
	}

	public Optional<Produto> findById(UUID id) {

		return produtoRepository.findById(id);
	}

	public void delete(UUID id) {
		 produtoRepository.deleteById(id);
	}
}
