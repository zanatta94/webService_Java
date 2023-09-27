package com.felipeZanatta.webService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.repositories.User_Repository;
import com.felipeZanatta.webService.services.exceptions.DatabaseException;
import com.felipeZanatta.webService.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;


// @Component registra a classe no mecanismo gestão de dependecia
// @Component para registrar no mecanismo de injeção de dependencia ocorrer na camada de controle (Resource)
// @Service para registrar um serviço na camada de serviço (Usado para ser mais específico) camada de serviço
// Tb exite o @Repositoy para registrar um repository
// Optional é um modelo de objeto a partir do Java8
// no insert o método save por padrão já returna o método salvo, é só por o return
// no update o getRefenceById vai instaciar o User mas apensa prapar o objeto monitorado para mexer e dps efetuar a operação com o BD
// metodo updateData criado para utlizar no método update
// findById o orElseThrow() vai tentar dar o get, se não tiver o User ele vai lançar a exceção personalziada com a expressão lambda dentro dos pareteses

@Service
public class User_Service {
	
	@Autowired
	private User_Repository repository;
	
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(long id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			}
			else {
				throw new ResourceNotFoundException(id);
			}
		} 
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}	
	}
	
	public User update(long id, User obj) {
		
		try {
			User entity = repository.getReferenceById(id);
			
			updataData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	private void updataData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

}
