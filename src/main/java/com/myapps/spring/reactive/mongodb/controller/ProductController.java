package com.myapps.spring.reactive.mongodb.controller;

import com.myapps.spring.reactive.mongodb.dto.ProductDto;
//import com.myapps.spring.reactive.mongodb.entity.Product;
import com.myapps.spring.reactive.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDto> getAllProducts()
    {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Flux<ProductDto> getProductById(@PathVariable  String id)
    {
        return productService.getProducts();
    }
    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max)
    {
        return productService.getProductsInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody  Mono<ProductDto> productDto)
    {
        return productService.saveProduct(productDto);

    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id)
    {
        return productService.updateProduct(productDtoMono, id);

    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return  productService.deleteProduct(id);
    }

}
