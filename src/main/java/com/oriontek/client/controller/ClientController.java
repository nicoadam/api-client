package com.oriontek.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.oriontek.client.dto.AddressDTO;
import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.model.Address;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.AddressRepository;
import com.oriontek.client.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> getClient() {

        List<ClientDTO> clientDTOList = this.clientRepository.findAll()
                .stream().map(cli -> {
                    ClientDTO dto =new ClientDTO();
                    dto.setId(cli.getId());
                    dto.setName(cli.getName());
                    dto.setAge(cli.getAge());
                    dto.setLastname(cli.getLastname());
                    dto.setAddress(
                            cli.getAddresses().stream().map(address -> {
                                return new AddressDTO(address.getId(), address.getLocation());
                            }).collect(Collectors.toList())
                    );
                    return dto;
        }).collect(Collectors.toList());

        
    if (Objects.isNull(clientDTOList)) {
            return new ResponseEntity<>(clientDTOList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {

        Optional<Client> opt = this.clientRepository.findById(id);

        if(opt.isPresent()) {
            Client cli= opt.get();
            ClientDTO dto =new ClientDTO();
            dto.setName(cli.getName());
            dto.setLastname(cli.getLastname());
            dto.setAge(cli.getAge());

            dto.setAddress(cli.getAddresses()
                    .stream()
                .map(address -> {
                    return new AddressDTO(address.getId(), address.getLocation());
                }).collect(Collectors.toList()));

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/add")
    public ResponseEntity saveClient(@RequestBody ClientDTO clientDTO) {

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        for(AddressDTO addressDTO: clientDTO.getAddress()) {
            Address addr= new Address();
            addr.setLocation(addressDTO.getLocation());
            addr.setClient(client);
            client.addAddress(addr);
        }

        Client save = this.clientRepository.save(client);

        if (Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateClient(@RequestBody ClientDTO clientDTO) {

        if(Objects.isNull(clientDTO) || Objects.isNull(clientDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Client client = this.clientRepository.getById(clientDTO.getId());

        if( Objects.isNull(client.getId())){
            return ResponseEntity.notFound().build();
        }
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        client.getAddresses().clear();

        for(AddressDTO addressDTO: clientDTO.getAddress()) {
            Address addr= new Address();
            addr.setLocation(addressDTO.getLocation());
            addr.setClient(client);
            client.addAddress(addr);
        }

        Client save = this.clientRepository.save(client);

        if (Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteByIdClient(@PathVariable Long id) throws Exception {

        Client client = this.clientRepository.getById(id);

        if (Objects.isNull(client.getId())) {
            return ResponseEntity.notFound().build();
        }
        this.clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteClient(@RequestBody ClientDTO clientDTO) throws Exception {

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        this.clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }

}
