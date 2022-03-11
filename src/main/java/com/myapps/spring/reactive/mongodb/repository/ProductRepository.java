package com.myapps.spring.reactive.mongodb.repository;

import com.myapps.spring.reactive.mongodb.dto.ProductDto;
//import com.myapps.spring.reactive.mongodb.entity.Product;
import com.myapps.spring.reactive.mongodb.entity.Products;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Products,String> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
