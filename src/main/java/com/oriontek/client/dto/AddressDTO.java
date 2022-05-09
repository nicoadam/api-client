package com.oriontek.client.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddressDTO implements Serializable {
    private long id;  
    @JsonProperty("street")
    private String street_location;
    private ClientDTO client;
}
