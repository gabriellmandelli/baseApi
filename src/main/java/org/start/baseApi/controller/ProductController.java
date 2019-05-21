package org.start.baseApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Product;
import org.start.baseApi.respository.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> findAll(@RequestParam("idManager")UUID idManager) {

        return productRepository.findAll().stream()
                      .filter(product -> product.getManager().getId().equals(idManager))
                      .collect(Collectors.toList());
    }

    @GetMapping(value = "{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product find(@RequestParam("idManager") UUID idManager,
                        @PathVariable("idProduct") UUID idProduct) {

        return productRepository.findById(idProduct)
                                .filter(product -> product.getManager().getId() == idManager)
                                .orElse(null);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product save(@RequestParam("idManager") UUID idManager,
                        @RequestBody Product Product) {

        Product.setId(UUID.randomUUID());
        return productRepository.save(Product);
    }

    @PutMapping(value = "{idProduct}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product edit(@RequestParam("idManager") UUID idManager,
                        @PathVariable("idProduct") UUID idProduct,
                        @RequestBody Product Product) {

        Product.setId(idProduct);
        return productRepository.save(Product);
    }

    @DeleteMapping(value = "{idProduct}")
    public void delete(@RequestParam("idManager") UUID idManager,
                       @PathVariable("idProduct") UUID idProduct){

        productRepository.deleteById(idProduct);
    }

    @DeleteMapping(value = "")
    public void deleteAll(@RequestParam("idManager") UUID idManager){
        productRepository.deleteAll(productRepository.findAll()
                                                        .stream()
                                                        .filter(product -> product.getManager().getId() == idManager)
                                                        .collect(Collectors.toList()));
    }

}
