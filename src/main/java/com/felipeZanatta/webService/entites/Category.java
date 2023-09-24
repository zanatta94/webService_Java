package com.felipeZanatta.webService.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



// O Set é uma coleçaõ e usa-se para evitar inserir um objeto igual dentro da lista
// Set da associação representa um conjunto, assim evita que exitema categorias repetidas na coleção
// O Set é uma interface, então para instaciar usei HashSet por ser uma classe correspondente da interface
// Para coleções cria-se apenas o GET, pois não faz sentido trocar uma coleção com SET, mas sim add ou remover elementos
// Coleções não se coloca no construtor
// mappedBy dentro do ManyToMany é para mapear a associação que tem com classe Product
// usei JsonIgnore para evitar loop infinito dessas associação bidirecional entre Category e Product



@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	
	
	// -------- ASSOCIAÇÕES --------
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();
	
	
	
	
	

	public Category() {
		
	}

	public Category(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return id == other.id;
	}
	
	
	
	

}
