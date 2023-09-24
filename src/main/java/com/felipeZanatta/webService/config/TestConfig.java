package com.felipeZanatta.webService.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipeZanatta.webService.entites.Category;
import com.felipeZanatta.webService.entites.Order;
import com.felipeZanatta.webService.entites.OrderItem;
import com.felipeZanatta.webService.entites.Product;
import com.felipeZanatta.webService.entites.User;
import com.felipeZanatta.webService.enums.Order_Status;
import com.felipeZanatta.webService.repositories.Category_Repository;
import com.felipeZanatta.webService.repositories.OrderItem_Repository;
import com.felipeZanatta.webService.repositories.Order_Repository;
import com.felipeZanatta.webService.repositories.Product_Repository;
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
	
	@Autowired
	private Product_Repository productRepository;
	
	@Autowired
	private OrderItem_Repository orderItemRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		
		Category cat1 = new Category(0l, "Electronics");
		Category cat2 = new Category(0l, "Books");
		Category cat3 = new Category(0l, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(0l, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(0l, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(0l, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(0l, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(0l, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		// Adiciona uma catagoria a coleção de categorias da classe Product
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		// para salvar as associações aqui no JPA se salva de novo os pordutos após as modificações. No SQL é diferente!
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		
		User u1 = new User(0l, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(0l, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Order o1 = new Order(0l, Instant.parse("2019-06-20T19:53:07Z"), u1, Order_Status.PAID);
		Order o2 = new Order(0l, Instant.parse("2019-07-21T03:42:10Z"), u2, Order_Status.WATTING_PAYMENT);
		Order o3 = new Order(0l, Instant.parse("2019-07-22T15:21:22Z"), u1, Order_Status.WATTING_PAYMENT);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		
		
	}
	
	
	
	
}
