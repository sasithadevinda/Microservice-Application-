package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.dto.request.ProductRequest;
import com.programmingtechie.productservice.dto.response.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product =Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product {} Saved"+new Date().getTime(),product.getId());


    }

    public List<ProductResponse>  getAllProducts(){
        List<Product> products=productRepository.findAll();
       return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().name(product.getName())
                .price(product.getPrice())
                .id(product.getId())
                .description(product.getDescription())
                .build();
    }
}
