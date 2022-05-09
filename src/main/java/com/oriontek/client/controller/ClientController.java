package com.oriontek.client.controller;

import java.util.List;
import java.util.Objects;

import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.model.Address;
import com.oriontek.client.model.Client;
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
@RequestMapping("/api/client")
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressController addressController;    

    @GetMapping("/get")
    public ResponseEntity<List<Client>> getClient() {
        
        return new ResponseEntity<>(this.clientRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = this.clientRepository.findById(id).get();
        if(Objects.isNull(client)) {
            return new ResponseEntity<>(client,HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(client,HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/address-per-client/{id}")
    public ResponseEntity<Client> getAddressPerClient(@PathVariable Long id) {
        //Client client = this.clientRepository.
        Address address = this.addressController.getClientById(id);
        if(Objects.isNull(client)) {
            return new ResponseEntity<>(client,HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(client,HttpStatus.NOT_FOUND);
    }


    @PostMapping("/save-client")
    public ResponseEntity saveClient(@RequestBody ClientDTO clientDTO) {
        
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        Client save = this.clientRepository.save(client);

        if(Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
         return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteByIdClient(@PathVariable Long id) throws Exception{
        
        Client save = this.clientRepository.getById(id);

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
