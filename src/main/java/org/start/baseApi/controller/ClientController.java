package org.start.baseApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Client;
import org.start.baseApi.respository.ClientRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Client> findAll(@RequestParam("idSalesman") UUID idSalesman) {
        return clientRepository.findAll()
                                .stream().filter(client -> client.getSalesman().getId() == idSalesman)
                                .collect(Collectors.toList());
    }

    @GetMapping(value = "{idClient}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Client find(@RequestParam("idSalesman") UUID idSalesman,
                       @PathVariable("idClient") UUID idClient) {
        return clientRepository.findById(idClient).orElse(null);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Client save(@RequestParam("idSalesman") UUID idSalesman,
                       @RequestBody Client Client) {

        Client.setId(UUID.randomUUID());
        return clientRepository.save(Client);
    }

    @PutMapping(value = "{idClient}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Client edit(@RequestParam("idSalesman") UUID idSalesman,
                       @PathVariable("idClient") UUID idClient,
                       @RequestBody Client Client) {

        Client.setId(idClient);
        return clientRepository.save(Client);
    }

    @DeleteMapping(value = "{idClient}")
    public void delete(@RequestParam("idSalesman") UUID idSalesman,
                       @PathVariable("idClient") UUID idClient){
        clientRepository.deleteById(idClient);
    }

    @DeleteMapping(value = "")
    public void deleteAll(@RequestParam("idSalesman") UUID idSalesman){
        clientRepository.deleteAll(clientRepository.findAll().stream()
                                                    .filter(client -> client.getSalesman().getId() == idSalesman)
                                                    .collect(Collectors.toList()));
    }

}
