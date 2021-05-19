package com.springboot.api.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.domain.Categoria;
import com.springboot.api.domain.Cidade;
import com.springboot.api.domain.Cliente;
import com.springboot.api.domain.Endereco;
import com.springboot.api.domain.Estado;
import com.springboot.api.domain.ItemPedido;
import com.springboot.api.domain.Pagamento;
import com.springboot.api.domain.PagamentoBoleto;
import com.springboot.api.domain.PagamentoCartao;
import com.springboot.api.domain.Pedido;
import com.springboot.api.domain.Produto;
import com.springboot.api.domain.enums.EstadoPagamento;
import com.springboot.api.domain.enums.TipoCliente;
import com.springboot.api.repository.CategoriaRepository;
import com.springboot.api.repository.CidadeRepository;
import com.springboot.api.repository.ClienteRepository;
import com.springboot.api.repository.EnderecoRepository;
import com.springboot.api.repository.EstadoRepository;
import com.springboot.api.repository.ItemPedidoRepository;
import com.springboot.api.repository.PagamentoRepository;
import com.springboot.api.repository.PedidoRepository;
import com.springboot.api.repository.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepositorio;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 3000.00);
		Produto p3 = new Produto(null, "mouse", 8000.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepositorio.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Mina Gerais");
		Estado est2 = new Estado(null, "São paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "são paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "joana sara ", "joanasousa678@gmail.com", "60902212362",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("087989822", "9898523"));

		Endereco e1 = new Endereco(null, "rua floes", "110", "apto 302", "jardim", "330245", cli1, c1);
		Endereco e2 = new Endereco(null, "rua dore", "110", "apto 302", "jardim", "330245", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/05/2019 10:32"), cli1, e2);

		Pagamento pagt1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);

		Pagamento pagt2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/10/2020 00:00"),
				null);
		ped2.setPagamento(pagt2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pagt1, pagt2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 2000.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 2000.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
