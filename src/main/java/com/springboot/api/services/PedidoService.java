package com.springboot.api.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.api.domain.ItemPedido;
import com.springboot.api.domain.PagamentoBoleto;
import com.springboot.api.domain.Pedido;
import com.springboot.api.domain.enums.EstadoPagamento;
import com.springboot.api.repository.ItemPedidoRepository;
import com.springboot.api.repository.PagamentoRepository;
import com.springboot.api.repository.PedidoRepository;
import com.springboot.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepositorio;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	@Autowired
	private ClienteSevice clienteService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = repo.save(obj);
		pagamentoRepositorio.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
		    ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setValor(ip.getProduto().getValor());
			ip.setPedido(obj);
		}
		itemPedidoRepo.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
	
	

}