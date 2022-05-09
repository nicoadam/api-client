package com.oriontek.client.dto;

import java.io.Serializable;
import java.util.List;

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
    private List<AddressDTO> address;
}
