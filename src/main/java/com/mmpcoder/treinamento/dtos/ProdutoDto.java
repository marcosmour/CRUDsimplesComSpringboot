package com.mmpcoder.treinamento.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class ProdutoDto {

	@NotBlank
	private String nome;
	private BigDecimal preco;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
}
