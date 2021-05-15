package com.springboot.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springboot.api.domain.Cliente;

import com.springboot.api.repository.ClienteRepository;
import com.springboot.api.services.exceptions.DataIntegrityExcepetion;
import com.springboot.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteSevice {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExcepetion("não e possivel Ecluir categoria que possui produtor");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

}
