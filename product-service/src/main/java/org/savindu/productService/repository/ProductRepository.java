package org.savindu.productService.repository;


import org.savindu.productService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, Long> {
}

