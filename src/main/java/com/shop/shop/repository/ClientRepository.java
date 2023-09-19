package com.shop.shop.repository;

import com.shop.shop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);



}
