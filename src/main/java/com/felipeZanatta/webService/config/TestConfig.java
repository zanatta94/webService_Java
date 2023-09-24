package com.felipeZanatta.webService.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipeZanatta.webService.entites.Category;
import com.felipeZanatta.webService.entites.Order;
import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.enums.Order_Status;
import com.felipeZanatta.webService.repositories.Category_Repository;
import com.felipeZanatta.webService.repositories.Order_Repository;
import com.felipeZanatta.webService.repositories.User_Repository;


// Esta é uma classe auxiliar de configuração, para popular o BD com alguns objetos
// @Configuration para indicar que é uma classe auxiliar de conf
// @Profile pra indicar o perfil conforme no arquiqui application-test
// CommandLineRunner para executar o que estiver dentro do metedo run quando o programa for iniciado
// @Autowired associa uma instacia do User_Repository nesta classe
// Instant ISO8601, esse formato da ISO é um padrão com várias possibilidades
//	mais comum é "yyyy-MM-ddThh:mm:ssZ" -> o Z significa que está no padrão UTC




@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	 
	@Autowired
	private User_Repository userRepository;
	
	@Autowired
	private Order_Repository orderRepository;
	
	@Autowired
	private Category_Repository categoryRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		
		Category cat1 = new Category(0l, "Electronics");
		Category cat2 = new Category(0l, "Books");
		Category cat3 = new Category(0l, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(0l, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(0l, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Order o1 = new Order(0l, Instant.parse("2019-06-20T19:53:07Z"), u1, Order_Status.PAID);
		Order o2 = new Order(0l, Instant.parse("2019-07-21T03:42:10Z"), u2, Order_Status.WATTING_PAYMENT);
		Order o3 = new Order(0l, Instant.parse("2019-07-22T15:21:22Z"), u1, Order_Status.WATTING_PAYMENT);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		
		
	}
	
	
	
	
}
