package com.springboot.api.dto;

import java.io.Serializable;

import com.springboot.api.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;
	private String email;
	private String cpfUoCnpj;

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cpfUoCnpj = obj.getCpfoucnpj();

	}

	public ClienteDTO() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfUoCnpj() {
		return cpfUoCnpj;
	}

	public void setCpfoucnpj(String cpfUoCnpj) {
		this.cpfUoCnpj = cpfUoCnpj;
	}

	

}
