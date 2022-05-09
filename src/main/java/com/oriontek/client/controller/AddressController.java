package com.oriontek.client.controller;

import java.util.List;
import java.util.Objects;

import com.oriontek.client.dto.AddressDTO;
import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.model.Address;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.AddressRepository;
import com.oriontek.client.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Address>> getAddress() {
        
        return new ResponseEntity<>(this.addressRepository.findAll(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getClientById(@PathVariable Long id) {
        Address address = this.addressRepository.findById(id).get();
        if(Objects.isNull(address)) {
            return new ResponseEntity<>(address,HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(address,HttpStatus.NOT_FOUND);
    }



    @PostMapping("/save-address")
    public ResponseEntity saveAddress(@RequestBody AddressDTO addressDTO) {
        
        Address address=new Address();
        address.setStreet_location(addressDTO.getStreet_location());
        address.setClient(new Client(addressDTO.getClient().getName(), 
            addressDTO.getClient().getLastname(), addressDTO.getClient().getAge()))
            ;

        Address save = this.addressRepository.save(address);

        if(Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
         return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteByIdAddress(@PathVariable Long id) throws Exception{
        
        Address save = this.addressRepository.getById(id);

        if(Objects.isNull(save)) {
            return ResponseEntity.notFound().build();
        }
        this.clientRepository.deleteById(id);
         return ResponseEntity.ok().build();
    }



    @DeleteMapping
    public ResponseEntity deleteClient(@RequestBody ClientDTO clientDTO) throws Exception{
        
        Client client =new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        this.clientRepository.delete(client);

         return ResponseEntity.ok().build();
    }

}
