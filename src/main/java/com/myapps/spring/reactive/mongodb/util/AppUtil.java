package com.myapps.spring.reactive.mongodb.util;

import com.myapps.spring.reactive.mongodb.dto.ProductDto;
import com.myapps.spring.reactive.mongodb.entity.Products;
import org.springframework.beans.BeanUtils;

public class AppUtil {

    public static ProductDto entityToDto(Products product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Products dtoToEntity(ProductDto productDto){
        Products product = new Products();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
