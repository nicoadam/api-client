package com.oriontek.client.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddressDTO implements Serializable {
    private long id;
    @JsonProperty("location")
    private String location;

    public AddressDTO(long id, String location) {
        this.id = id;
        this.location = location;
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("AddressDTO{")
                .append("location = '").append(location).append("'")
                        .append("}")
        ;
        return sb.toString();
    }
}
