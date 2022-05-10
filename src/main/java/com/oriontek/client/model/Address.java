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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;


    public Address(String location, Client client){
        this.location = location;
        this.setClient(client);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client )) return false;
        return id != null && id.equals(((Client) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
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
