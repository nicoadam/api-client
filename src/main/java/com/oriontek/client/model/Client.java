package com.oriontek.client.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private Integer age;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Address> address;

    public Client(String name, String lastname, Integer age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Client() {

    }

}
