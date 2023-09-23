package com.felipeZanatta.webService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeZanatta.webService.entites.Order;


// O repositório extende o JpaRepositoóry passado a entidade e o tipo do id
// Todos repositories interface, JpaRepository tambem é
// Não precisa criar a implementação pq extende a implemntação padrão do JpaRepository
// @Repository não inserido pois já herda da extensão


public interface Order_Repository extends JpaRepository<Order, Long> {

}
