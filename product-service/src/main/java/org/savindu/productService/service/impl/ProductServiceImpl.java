package org.savindu.productService.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.savindu.productService.dto.ProductRequest;
import org.savindu.productService.dto.ProductResponse;
import org.savindu.productService.model.Product;
import org.savindu.productService.repository.ProductRepository;
import org.savindu.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product created: {}", product.getName());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> all = productRepository.findAll();
        return all.stream()
                .map(this::mapToProductResponse)
                .toList();


    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
