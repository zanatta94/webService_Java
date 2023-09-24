package com.felipeZanatta.webService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeZanatta.webService.entites.Category;
import com.felipeZanatta.webService.entites.Product;
import com.felipeZanatta.webService.entites.User;


// O repositório extende o JpaRepositoóry passado a entidade e o tipo do id
// Todos repositories interface, JpaRepository tambem é
// Não precisa criar a implementação pq extende a implemntação padrão do JpaRepository
// @Repository não inserido pois já herda da extensão


public interface Product_Repository extends JpaRepository<Product, Long>{

}
