package com.oriontek.client.repository;

import com.oriontek.client.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
