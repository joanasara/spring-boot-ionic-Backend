package com.springboot.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.api.domain.Pedido;

import com.springboot.api.repository.PedidoRepository;
import com.springboot.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}
	public Pedido insert(Pedido obj) {
		 obj.setId(null);
		 return repo.save(obj);
	 }

}