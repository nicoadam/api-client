package com.oriontek.client.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ClientDTO implements Serializable {
    private String name;
    private String lastname;
    private Integer age;
    private List<AddressDTO> address;
}
