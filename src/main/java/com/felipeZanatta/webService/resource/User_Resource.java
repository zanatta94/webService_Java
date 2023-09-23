package com.felipeZanatta.webService.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipeZanatta.webService.entites.User;


//Classe da camada de recursos
//RequestController é para identificar a classe como um recurso web implementado por um controlador Rest
//RequestMapping para dar um nome ao recurso
@RestController
@RequestMapping(value = "/users")
public class User_Resource {
	
	
	//ResponseEntity tipo específico do spring para retornar respostas de requisições web
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1l, "Felipe", "felipe@gmail.com", "51995355", "123456");
		
		//o returno .Ok é pra retornar resposta com sucesso n HTTP
		//o body é para retornar o corpo da resposta, neste caso o obj u
		return ResponseEntity.ok().body(u);
	}

}
