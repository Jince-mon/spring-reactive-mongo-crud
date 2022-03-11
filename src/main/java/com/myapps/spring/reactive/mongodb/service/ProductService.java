package com.myapps.spring.reactive.mongodb.service;

import com.myapps.spring.reactive.mongodb.dto.ProductDto;
//import com.myapps.spring.reactive.mongodb.entity.Product;
import com.myapps.spring.reactive.mongodb.repository.ProductRepository;
import com.myapps.spring.reactive.mongodb.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(AppUtil::entityToDto);
    }

    public Mono<ProductDto> getProductById(String Id) {
        return productRepository.findById(Id).map(AppUtil::entityToDto);
    }

    public Flux<ProductDto> getProductsInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(AppUtil::dtoToEntity)
                .flatMap(productRepository::save)
                .map(AppUtil::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtil::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtil::entityToDto);
    }

    public Mono<Void> deleteProduct(String id)
    {
        return productRepository.deleteById(id);
    }
}
