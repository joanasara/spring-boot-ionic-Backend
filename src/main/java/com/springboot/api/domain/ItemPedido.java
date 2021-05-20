package com.springboot.api.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;
	private Integer quantia;
	private Double valor;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantia, Double valor) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantia = quantia;
		this.valor = valor;
	}
    
	public double getSubTotal() {
		return (valor - desconto) * quantia;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
   public void setPedido(Pedido pedido) {
	   id.setPedido(pedido);
   }
	
	public Produto getProduto() {
		return id.getProduto();
	}
 
	public void setProduto(Produto produto) {
	  id.setProduto(produto);	
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantia() {
		return quantia;
	}

	public void setQuantia(Integer quantia) {
		this.quantia = quantia;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = 	NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte: ");
	    builder.append(getQuantia());	
	    builder.append(", Preço unitario:  ");
	    builder.append(nf.format(getValor()));
	    builder.append(", SubTotal: ");
	    builder.append(nf.format(getSubTotal()));
	    builder.append("\n");
	    return builder.toString();
	   
	}
	
	

}
