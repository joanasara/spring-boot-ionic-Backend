package com.springboot.api.dto;

import java.io.Serializable;

import com.springboot.api.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valor;

	public ProdutoDTO() {

	}
	public ProdutoDTO(Produto obj) {
      id = obj.getId();
      nome = obj.getNome();
      valor = obj.getValor();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
