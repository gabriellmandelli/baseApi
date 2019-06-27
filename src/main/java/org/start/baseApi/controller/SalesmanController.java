package org.start.baseApi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Salesman;
import org.start.baseApi.respository.SalesmanRepository;
import org.start.baseApi.util.authentication.ProjectToken;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("salesman")
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
    public String authenticateUser(@RequestBody Salesman entity) {

        ProjectToken projectToken = new ProjectToken();
        JSONObject jsonReturn = new JSONObject();
        
        Salesman lSalesman = salesmanRepository.findByLoginPassword( entity.getLogin());

        if (lSalesman == null) {
            jsonReturn.put("path", "/salesman/login");
            jsonReturn.put("message", "Invalid Token");
            jsonReturn.put("error", Response.Status.UNAUTHORIZED);
            jsonReturn.put("status", Response.Status.UNAUTHORIZED.getStatusCode());
        }else{
            jsonReturn.put("status", Response.Status.ACCEPTED.toString());
            jsonReturn.put("userId", lSalesman.getId().toString());
            jsonReturn.put("token", projectToken.genereteToken(lSalesman.getLogin()));
        }
        
        return jsonReturn.toString();
    }
}
