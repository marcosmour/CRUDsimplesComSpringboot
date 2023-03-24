package com.mmpcoder.treinamento.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmpcoder.treinamento.dtos.ProdutoDto;
import com.mmpcoder.treinamento.models.Produto;
import com.mmpcoder.treinamento.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto produtoDto){
		Produto produto = new Produto();
		BeanUtils.copyProperties(produtoDto, produto);
		produto.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
	}
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.finAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id){
		Optional<Produto> produtoOptional = produtoService.findById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
		Optional<Produto> produtoOptioinal = produtoService.findById(id);
		if(!produtoOptioinal.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado!");
		}
		produtoService.delete(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") UUID id, @RequestBody ProdutoDto produtoDto){
		Optional<Produto> produtoOptional = produtoService.findById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		
		Produto produto = produtoOptional.get(); 
		produto.setNome(produtoDto.getNome());
		produto.setPreco(produtoDto.getPreco());
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));
	}
}
