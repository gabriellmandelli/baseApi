package org.start.baseApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Salesman;
import org.start.baseApi.respository.SalesmanRepository;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class SalesmanController {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Salesman> findAll(@RequestParam("idManager") UUID idManager) {

        return salesmanRepository.findAll().stream()
                                .filter(salesman -> salesman.getManager().getId().equals(idManager))
                                .collect(Collectors.toList());
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Salesman save(@RequestParam("idManager") UUID idManager,
                         @RequestBody Salesman Salesman) {

        Salesman.setId(UUID.randomUUID());
        return salesmanRepository.save(Salesman);
    }

    @DeleteMapping(value = "")
    public void delete(@RequestParam("idSalesman") UUID idSalesman){
        salesmanRepository.deleteById(idSalesman);
    }

    @GetMapping(value = "{idSalesman}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Optional<Salesman> find(@RequestParam("idManager") UUID idManager,
                                   @PathVariable("idSalesman") UUID idSalesman) {
        return salesmanRepository.findById(idSalesman)
                                .filter(salesman -> salesman.getManager().getId().equals(idManager)) ;
    }

    @PutMapping(value = "{idSalesman}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Salesman edit(@RequestParam("idManager") UUID idManager,
                         @PathVariable("idSalesman") UUID idSalesman,
                         @RequestBody Salesman salesman) {

        if (salesman.getId().equals(null)){
            salesman.setId(idSalesman);
        }
        return salesmanRepository.save(salesman);
    }

    @PostMapping(value = "login", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public Response authenticateUser(@RequestBody Salesman entity) {

        Salesman lSalesman = salesmanRepository.findByLoginPassword( entity.getLogin());

        if (lSalesman == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }else{
            return Response.ok(Response.Status.ACCEPTED).build();
        }
    }
}
