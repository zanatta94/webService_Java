package com.felipeZanatta.webService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.repositories.User_Repository;

import jakarta.persistence.Entity;


// @Component registra a classe no mecanismo gestão de dependecia
// @Component para registrar no mecanismo de injeção de dependencia ocorrer na camada de controle (Resource)
// @Service para registrar um serviço na camada de serviço (Usado para ser mais específico) camada de serviço
// Tb exite o @Repositoy para registrar um repository
// Optional é um modelo de objeto a partir do Java8
// no insert o método save por padrão já returna o método salvo, é só por o return
// no update o getRefenceById vai instaciar o User mas apensa prapar o objeto monitorado para mexer e dps efetuar a operação com o BD


@Service
public class User_Service {
	
	@Autowired
	private User_Repository repository;
	
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public User update(long id, User obj) {
		User entity = repository.getReferenceById(id);
		
		updataData(entity, obj);
		return repository.save(entity);
	}

	private void updataData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

}
