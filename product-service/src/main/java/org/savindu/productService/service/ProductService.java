package org.savindu.productService.service;

import org.savindu.productService.dto.ProductRequest;
import org.savindu.productService.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
