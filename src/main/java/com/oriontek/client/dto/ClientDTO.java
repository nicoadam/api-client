package com.oriontek.client.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {
    private long id;
    private String name;
    private String lastname;
    private Integer age;
    @JsonProperty("address")
    private List<AddressDTO> address;

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("ClientDTO{")
                .append("name = '").append(name).append("',")
                .append("lastname = '").append(lastname).append("',")
                .append("age = '").append(age).append("'")
                .append("}")
                ;
        return sb.toString();
    }
}
