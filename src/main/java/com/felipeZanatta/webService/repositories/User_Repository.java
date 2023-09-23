package com.felipeZanatta.webService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeZanatta.webService.entites.User;


//O repositório extende o JpaRepositoóry passado a entidade e o tipo do id
//todos repositories interface, JpaRepository tambem é
//não precisa criar a implementação pq extende a implemntação padrão do JpaRepository
public interface User_Repository extends JpaRepository<User, Long>{

}
