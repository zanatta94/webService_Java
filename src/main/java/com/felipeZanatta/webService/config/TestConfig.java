package com.felipeZanatta.webService.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.repositories.User_Repository;


// Esta é uma classe auxiliar de configuração, para popular o BD com alguns objetos
// @Configuration para indicar que é uma classe auxiliar de conf
// @Profile pra indicar o perfil conforme no arquiqui application-test
// CommandLineRunner para executar o que estiver dentro do metedo run quando o programa for iniciado
// @Autowired associa uma instacia do User_Repository nesta classe

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private User_Repository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(0l, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(0l, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
	
	
	
	
}
