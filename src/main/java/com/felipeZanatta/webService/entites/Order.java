package com.felipeZanatta.webService.entites;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipeZanatta.webService.enums.Order_Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


// Classe Instante melhor que Date. Surgiu a partir Java8
// @ManyToOne muitos para um (Muitos Order para um User). Comforme projeto
//		quando feita requisição do lado do muitos, o lado do um é puxado automatico (lazy loading)
// JoimColum nome da chave estrangeira que está no BD
// JsonFormat para formatar Instant no padrao ISO8601
// No atributo orderstatus é Integer para referenciar o valor inserido pelo usuario
// getOrderStatus recebe o valor em int e converte para Order_Status em pelo metodo valueOf do enum Order_Status
// setOrderStatus recebe o valor com enum Order_Staus e converte para int pelo metodo getCode() do enum Order_Status
//		ainda o if é pra garantir q não seja inseirdo valor null
// no construtor o valor atribuido a variavel orderStatus é feito pelo metodo set
// na associacao items o mappedBy mapeia para o objeto order da classe OrderItem_pk.
//		assim mapeia uma Order para muitos OrderItem
// cascade mapeando as duas entidades para ter o mesmo id, no caso de mapear OneToOne com id ele se torna obrigatório
//		se o Payment for cód 5 o Order tb tem que ser o 5


@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	private Integer orderStatus;
	

	
	// -------- ASSOCIACOES DO PROJETO --------
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;


	
	public Order() {
		
	}

	public Order(long id, Instant moment, User client, Order_Status orderStatus) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	

	public Order_Status getOrderStatus() {
		return Order_Status.valueOf(orderStatus);
	}

	public void setOrderStatus(Order_Status orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		double sum = 0;
		
		for(OrderItem x : items) {
			sum += x.getSubTotal();
		}
		
		return sum;
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
		Order other = (Order) obj;
		return id == other.id;
	}

	
	
	
	

}
