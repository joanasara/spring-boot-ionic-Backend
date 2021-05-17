package com.springboot.api.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.api.domain.Cliente;
import com.springboot.api.domain.enums.TipoCliente;
import com.springboot.api.dto.ClienteNewDTO;
import com.springboot.api.repository.ClienteRepository;
import com.springboot.api.resources.exceptions.FieldMessage;
import com.springboot.api.services.validation.utils.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfUoCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "cpfOuCnpj invalido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfUoCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "cpfOuCnpj invalido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","message ja existente"));
		}
		
		

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
