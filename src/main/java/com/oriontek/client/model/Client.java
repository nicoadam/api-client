package com.oriontek.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private Integer age;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Client(String name, String lastname, Integer age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Client() {

    }

    public void addAddress(Address address){
        addresses.add(address);
        address.setClient(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setClient(this);
    }


    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Client{")
                .append("name = '").append(name).append("',")
                .append("lastname = '").append(lastname).append("',")
                .append("age = '").append(age).append("',")
                .append("address = [")
        ;

                int cont = 0;
              for(Address addr: addresses){
                  cont++;
                  sb.append("{").append(addr.getLocation()).append("}");
                  if(cont == addresses.size()){
                      sb.append("]}");
                  }else {
                      sb.append(",");
                  }

              }
        return sb.toString();
    }

}
