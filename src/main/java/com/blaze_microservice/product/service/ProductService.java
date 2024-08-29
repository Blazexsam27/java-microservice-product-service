package com.blaze_microservice.product.service;

import com.blaze_microservice.product.dto.ProductRequest;
import com.blaze_microservice.product.dto.ProductResponse;
import com.blaze_microservice.product.model.Product;
import com.blaze_microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        try {
            Product product = Product.builder().name(productRequest.name()).description(productRequest.description()).price(productRequest.price()).build();

            productRepository.save(product);
            log.info("Product Created");
            return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
        } catch (Exception e) {
            log.error("Error while creating product");
            throw new RuntimeException(e);
        }
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }
}
