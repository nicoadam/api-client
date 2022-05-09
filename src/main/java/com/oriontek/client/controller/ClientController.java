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

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> getClient() {

        List<ClientDTO> clientDTOList = this.clientRepository.findAll()
                .stream().map(cli -> {
                    ClientDTO dto =new ClientDTO();
                    dto.setName(cli.getName());
                    dto.setAge(cli.getAge());
                    dto.setLastname(cli.getLastname());
                    dto.setAddress(
                            cli.getAddress().stream().map(address -> {
                                return new AddressDTO(address.getId(), address.getStreet_location());
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

            dto.setAddress(cli.getAddress()
                    .stream()
                .map(address -> {
                    return new AddressDTO(address.getId(), address.getStreet_location());
                }).collect(Collectors.toList()));

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/add")
    public ResponseEntity saveClient(@RequestBody ClientDTO clientDTO) {

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setAge(clientDTO.getAge());

        List<Address> addressList=new ArrayList<>();
        clientDTO.getAddress()
                .stream()
                .forEach( addressDTO -> {
                    addressList.add(new Address(addressDTO.getId(), addressDTO.getStreet_location(), client));
                });

        client.setAddress(addressList);

        Client save = this.clientRepository.save(client);

        if (Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updateClient(@RequestBody ClientDTO dto) {

        if(Objects.isNull(dto) || Objects.isNull(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Client cli = this.clientRepository.getById(dto.getId());
        cli.setName(dto.getName());
        cli.setLastname(dto.getLastname());
        cli.setAge(dto.getAge());

        cli.setAddress(dto.getAddress()
                .stream()
                .map(address -> {
                    return new Address(address.getId(), address.getStreet_location(), cli);
                }).collect(Collectors.toList()));

        Client save = this.clientRepository.save(cli);

        if (Objects.isNull(save)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteByIdClient(@PathVariable Long id) throws Exception {

        Client save = this.clientRepository.getById(id);

        if (Objects.isNull(save)) {
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
