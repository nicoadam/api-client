package com.oriontek.client.repository;

import com.oriontek.client.model.Client;

import org.springframework.data.jpa.repository.JpaRepository; 

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
