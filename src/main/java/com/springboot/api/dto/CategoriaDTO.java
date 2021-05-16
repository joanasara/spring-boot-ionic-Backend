package com.springboot.api.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.springboot.api.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
 
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min=5, max=80, message= "O tamanho deve ser entre 5 e 80 caractere")
	private String nome;
	
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public CategoriaDTO() {

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

}
