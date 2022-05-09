package com.oriontek.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Address implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;  

    @Column
    private String street_location;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
}
