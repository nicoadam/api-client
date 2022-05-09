package com.oriontek.client.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddressDTO implements Serializable {
    private long id;  
    @JsonProperty("street")
    private String street_location;
    private ClientDTO client;

    public AddressDTO(long id, String street_location) {
        this.id = id;
        this.street_location = street_location;
    }
}
