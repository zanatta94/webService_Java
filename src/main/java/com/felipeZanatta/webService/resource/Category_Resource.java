package com.felipeZanatta.webService.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeZanatta.webService.entites.Category;
import com.felipeZanatta.webService.services.Category_Service;


// Classe da camada de recursos
// RequestController é para identificar a classe como um recurso web implementado por um controlador Rest
// RequestMapping para dar um nome ao recurso
// @GetMapping passando {id} para a passar o valor do id na url, pra indicar que tem um parametro
// @PathVariable para a requisição aceitar o parametro passado na URL
// ResponseEntity tipo específico do spring para retornar respostas de requisições web
// o return .Ok é pra retornar resposta com sucesso n HTTP
// o body é para retornar o corpo da resposta, neste caso a lista de usuario

@RestController
@RequestMapping(value = "/categories")
public class Category_Resource {
	
	@Autowired
	private Category_Service service;
	
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable long id) {
		Category obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	

}
