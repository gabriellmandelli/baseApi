package org.start.baseApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Sale;
import org.start.baseApi.model.Salesman;
import org.start.baseApi.respository.SaleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sale")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Sale> findAll(@RequestParam("idSalesman") UUID idSalesman) {

        return saleRepository.findAll()
                                .stream()
                                .filter(sale -> sale.getSalesman().getId().equals(idSalesman))
                                .collect(Collectors.toList());
    }

    @GetMapping(value = "{idSale}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sale find(@RequestParam("idSalesman") UUID idSalesman,
                     @PathVariable("idSale") UUID idSale) {
        return saleRepository.findById(idSale)
                                .filter(sale -> sale.getSalesman().getId().equals(idSalesman))
                                .orElse(null);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sale save(@RequestParam("idSalesman") UUID idSalesman,
                     @RequestBody Sale Sale) {

        Salesman salesman = new Salesman();
        salesman.setId(idSalesman);

        Sale.setId(UUID.randomUUID());
        Sale.setSalesman(salesman);

        return saleRepository.save(Sale);
    }

    @PutMapping(value = "{idSale}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sale edit(@RequestParam("idSalesman") UUID idSalesman,
                     @PathVariable("idSale") UUID idSale,
                     @RequestBody Sale Sale) {

        Salesman salesman = new Salesman();
        salesman.setId(idSalesman);

        if(Sale.getId().equals(null)){
            Sale.setId(idSale);
        }

        return saleRepository.save(Sale);
    }

    @DeleteMapping(value = "{idSale}")
    public void delete(@RequestParam("idSalesman") UUID idSalesman,
                       @PathVariable("idSale") UUID idSale){
        saleRepository.deleteById(idSale);
    }

    @DeleteMapping(value = "all")
    public void deleteAll(@RequestParam("idSalesman") UUID idSalesman){
        saleRepository.deleteAll(saleRepository.findAll()
                                                .stream()
                                                .filter(sale -> sale.getSalesman().getId().equals(idSalesman))
                                                .collect(Collectors.toList()));
    }

}
