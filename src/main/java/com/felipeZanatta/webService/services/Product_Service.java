package com.felipeZanatta.webService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeZanatta.webService.entites.Product;
import com.felipeZanatta.webService.repositories.Product_Repository;


// @Component registra a classe no mecanismo gestão de dependecia
// @Component para registrar no mecanismo de injeção de dependencia ocorrer na camada de controle (Resource)
// @Service para registrar um serviço na camada de serviço (Usado para ser mais específico) camada de serviço
// Tb exite o @Repositoy para registrar um repository
// Optional é um modelo de objeto a partir do Java8


@Service
public class Product_Service {
	
	@Autowired
	private Product_Repository repository;
	
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

}
