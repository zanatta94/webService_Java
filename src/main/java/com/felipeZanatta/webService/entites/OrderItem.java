package com.felipeZanatta.webService.entites;

import java.io.Serializable;
import java.util.Objects;

import com.felipeZanatta.webService.entites.pk.OrderItem_pk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


// o id é um objeto OrderItem_pk pois é uma chave composta
// no construtor o id é setado passando a Order e o Product que vem dos parametros
// o GET e o SET do id desta classe é feito utilizando os objetos da classe aux OrderItem_pk.
// @EmbeddedId pois o id se trata de uma chave com posta


@Entity
@Table(name = "tb_OrderItem")
public class OrderItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private OrderItem_pk id = new OrderItem_pk();
	private Integer quantity;
	private double price;
	
	
	
	
	public OrderItem() {
		
	}

	public OrderItem(Order order, Product product, Integer quantity, double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
