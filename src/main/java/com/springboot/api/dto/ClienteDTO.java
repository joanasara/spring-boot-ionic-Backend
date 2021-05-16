package com.springboot.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.springboot.api.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caractere")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caractere")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caractere")
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
