package com.felipeZanatta.webService.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.services.User_Service;


// Classe da camada de recursos
// RequestController é para identificar a classe como um recurso web implementado por um controlador Rest
// RequestMapping para dar um nome ao recurso
// @GetMapping apenas, sem passar na url nenhum paramentro retorna todos os User
// @GetMapping passando {id} para a passar o valor do id na url, pra indicar que tem um parametro
// @PathVariable para a requisição aceitar o parametro passado na URL
// ResponseEntity tipo específico do spring para retornar respostas de requisições web
// o return .Ok é pra retornar resposta com sucesso n HTTP
// o body é para retornar o corpo da resposta, neste caso a lista de usuario
// @RequestBody para chegar na hora da requisição via modo Json e descerializar para o um objeto User
// o mais adequado na hora do insert é ter um retorno 201
// No insert o ResponseEntity.created para gerar o retorno 201, mas ele espera um objeto do modelo URI
// A variavel uri é instaciada pela classe URI.
//		ServletUriComponentsBuilder -> usado para esanciar a URI
//		fromCurrentRequest().path() -> ele vai montar um padrão parareceber um recurso que vem na url após o /users. Neste caso o id
//		buildAndExpand -> espera o valor que inserir após o /users
// 		toUri -> para converter para uri
// no método delete usa-se o void pois não vai ser retornado nenhum corpo
// no content por ser uma resposata sem corpo e para tratar o cód 204 http


@RestController
@RequestMapping(value = "/users")
public class User_Resource {
	
	@Autowired
	private User_Service service;
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable long id) {
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
